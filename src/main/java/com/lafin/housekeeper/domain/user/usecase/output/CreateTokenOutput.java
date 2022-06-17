package com.lafin.housekeeper.domain.user.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CreateTokenOutput implements Output {

    private boolean result;

    private String message;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    public static CreateTokenOutput fail(String message) {
        return CreateTokenOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
