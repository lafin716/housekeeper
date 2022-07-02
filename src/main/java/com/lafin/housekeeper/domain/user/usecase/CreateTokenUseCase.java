package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.gateway.AuthGateway;
import com.lafin.housekeeper.domain.user.model.Auth;
import com.lafin.housekeeper.domain.user.model.TokenInfo;
import com.lafin.housekeeper.domain.user.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.user.usecase.output.CreateTokenOutput;
import com.lafin.housekeeper.domain.user.util.TokenUtils;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import com.lafin.housekeeper.shared.library.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateTokenUseCase implements UseCase<CreateTokenInput, CreateTokenOutput> {

    private final AuthGateway authGateway;

    @Override
    public CreateTokenOutput execute(CreateTokenInput input) throws InvalidInputException {
        input.validate();

        try {
            var plainAccessToken = TokenUtils.encodeAccessToken(TokenInfo.create(input.getId(), input.getEmail()));
            long expireMinutes = 60L;
            var plainRefreshToken = TokenUtils.encodeRefreshToken(TokenInfo.create(input.getId(), input.getEmail()), expireMinutes);
            var token = Auth.builder()
                    .accessToken(plainAccessToken)
                    .refreshToken(plainRefreshToken)
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
}
