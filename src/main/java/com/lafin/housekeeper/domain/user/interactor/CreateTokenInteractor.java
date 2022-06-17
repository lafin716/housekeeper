package com.lafin.housekeeper.domain.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.AuthGateway;
import com.lafin.housekeeper.domain.user.model.Auth;
import com.lafin.housekeeper.domain.user.usecase.CreateTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.user.usecase.output.CreateTokenOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.library.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateTokenInteractor implements CreateTokenUseCase {

    private final long expireMinutes = 60L;

    private final AuthGateway authGateway;

    @Override
    public CreateTokenOutput execute(CreateTokenInput input) throws InvalidInputException {
        input.validate();

        var aes = new AES256();
        var plainAccessToken = createPlainAccessToken(input.getId(), input.getEmail());
        var plainRefreshToken = createPlainRefreshToken(input.getId(), input.getEmail());

        try {
            var token = Auth.builder()
                    .accessToken(aes.encrypt(plainAccessToken))
                    .refreshToken(aes.encrypt(plainRefreshToken))
                    .createdAt(LocalDateTime.now())
                    .expiredAt(LocalDateTime.now().plusMinutes(expireMinutes))
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
                    .expiredAt(savedToken.getExpiredAt())
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
        builder.append(LocalDateTime.now().plusMinutes(expireMinutes));

        return builder.toString();
    }
}
