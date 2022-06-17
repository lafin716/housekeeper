package com.lafin.housekeeper.domain.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.AuthGateway;
import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.Auth;
import com.lafin.housekeeper.domain.user.usecase.CreateTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.user.usecase.output.CreateTokenOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.library.AES256;
import com.lafin.housekeeper.shared.status.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateTokenInteractor implements CreateTokenUseCase {

    private final UserGateway userGateway;

    private final AuthGateway authGateway;

    @Override
    public CreateTokenOutput execute(CreateTokenInput input) throws InvalidInputException {
        input.validate();

        var user = userGateway.findByEmail(input.getEmail());
        if (Objects.isNull(user)) {
            return CreateTokenOutput.fail("회원 정보를 찾을 수 없습니다.");
        }

        int failCount = user.getFailedSignIn();
        userGateway.save(user);
        if (failCount >= user.getLimitFailSignIn()) {
            return CreateTokenOutput.fail(messageOverFailSignIn(failCount, user.getLimitFailSignIn()));
        }

        if (!user.getPassword().equals(input.getPassword())) {
            user.failedSignIn();
            userGateway.save(user);
            return CreateTokenOutput.fail("회원정보가 일치하지 않습니다. 다시 한번 확인해주세요.");
        }

        if (user.getStatus() == UserStatus.ON_REVIEW) {
            return CreateTokenOutput.fail("승인되지 않은 회원입니다.");
        }

        if (user.getStatus() == UserStatus.BANNED) {
            return CreateTokenOutput.fail("관리자에 의해 정지 된 회원입니다.");
        }

        if (user.getStatus() == UserStatus.DORMANT) {
            return CreateTokenOutput.fail("휴면 회원입니다. 휴면해제 신청을 해주세요.");
        }

        if (user.getStatus() == UserStatus.LEFT) {
            return CreateTokenOutput.fail("탈퇴 된 회원입니다.");
        }

        var aes = new AES256();
        var plainAccessToken = createPlainAccessToken(user.getId(), user.getEmail());
        var plainRefreshToken = createPlainRefreshToken(user.getId(), user.getEmail());

        try {
            var token = Auth.builder()
                    .accessToken(aes.encrypt(plainAccessToken))
                    .refreshToken(aes.encrypt(plainRefreshToken))
                    .createdAt(LocalDateTime.now())
                    .build();
            var savedToken = authGateway.save(token);
            if (Objects.isNull(savedToken)) {
                return CreateTokenOutput.fail("토큰 생성 실패.");
            }
            
            return CreateTokenOutput.builder()
                    .result(true)
                    .accessToken(savedToken.getAccessToken())
                    .refreshToken(savedToken.getRefreshToken())
                    .createdAt(savedToken.getCreatedAt())
                    .build();
        } catch (Exception e) {
            return CreateTokenOutput.fail("토큰 생성 중 오류가 발생하였습니다.");
        }
    }

    private String createPlainAccessToken(Long id, String email) {
        var builder = new StringBuilder();
        builder.append(id);
        builder.append("|");
        builder.append(email);
        builder.append("|");
        builder.append(LocalDateTime.now());

        return builder.toString();
    }

    private String createPlainRefreshToken(Long id, String email) {
        var builder = new StringBuilder();
        builder.append(id);
        builder.append("|");
        builder.append(email);
        builder.append("|");
        builder.append(LocalDateTime.now().plusHours(1L));

        return builder.toString();
    }

    private String messageOverFailSignIn(int failCount, int limitCount) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("로그인 횟수를 초과하였습니다. ");
        errorMessage.append(failCount);
        errorMessage.append("/");
        errorMessage.append(limitCount);

        return errorMessage.toString();
    }
}
