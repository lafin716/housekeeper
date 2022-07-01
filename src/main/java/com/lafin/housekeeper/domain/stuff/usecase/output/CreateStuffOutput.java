package com.lafin.housekeeper.domain.stuff.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateStuffOutput implements Output {

    private boolean result;

    private String message;

    public static CreateStuffOutput ok(String message) {
        return CreateStuffOutput.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static CreateStuffOutput fail(String message) {
        return CreateStuffOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
