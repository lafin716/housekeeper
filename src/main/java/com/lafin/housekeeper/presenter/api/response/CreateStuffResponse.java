package com.lafin.housekeeper.presenter.api.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateStuffResponse implements ResponseModel {

    private boolean result;

    private String message;

    public static CreateStuffResponse ok(String message) {
        return CreateStuffResponse.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static CreateStuffResponse fail(String message) {
        return CreateStuffResponse.builder()
                .result(false)
                .message(message)
                .build();
    }
}
