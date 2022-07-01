package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.gateway.AuthGateway;
import com.lafin.housekeeper.domain.user.usecase.input.VerifyTokenInput;
import com.lafin.housekeeper.domain.user.usecase.output.VerifyTokenOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VerifyTokenUseCase implements UseCase<VerifyTokenInput, VerifyTokenOutput> {

    private final AuthGateway authGateway;

    @Override
    public VerifyTokenOutput execute(VerifyTokenInput input) throws InvalidInputException {
        input.validate();

        var auth = authGateway.findByAccessToken(input.getAccessToken());
        if (Objects.isNull(auth)) {
            return VerifyTokenOutput.fail("토큰 정보가 없습니다.");
        }

        if (LocalDateTime.now().isAfter(auth.getExpiredAt())) {
            return VerifyTokenOutput.fail("만료된 토큰입니다.");
        }

        return VerifyTokenOutput.ok("인증완료");
    }
}