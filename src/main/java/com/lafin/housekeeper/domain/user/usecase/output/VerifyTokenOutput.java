package com.lafin.housekeeper.domain.user.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyTokenOutput implements Output {

    private boolean result;

    private String message;

    public static VerifyTokenOutput ok(String message) {
        return VerifyTokenOutput.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static VerifyTokenOutput fail(String message) {
        return VerifyTokenOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
