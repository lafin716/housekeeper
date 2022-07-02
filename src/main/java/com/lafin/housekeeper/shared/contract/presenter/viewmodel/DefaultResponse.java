package com.lafin.housekeeper.shared.contract.presenter.viewmodel;

import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DefaultResponse implements ResponseModel {

    private boolean result;

    private String message;

    public static DefaultResponse ok(String message) {
        return DefaultResponse.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static DefaultResponse fail(String message) {
        return DefaultResponse.builder()
                .result(false)
                .message(message)
                .build();
    }

    public static DefaultResponse response(DefaultOutput output) {
        if (output.isResult()) {
            return ok(output.getMessage());
        }

        return fail(output.getMessage());
    }
}
