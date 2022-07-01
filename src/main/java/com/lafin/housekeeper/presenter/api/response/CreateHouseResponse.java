package com.lafin.housekeeper.presenter.api.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateHouseResponse implements ResponseModel {

    private boolean result;

    private String message;

    public static CreateHouseResponse ok(String message) {
        return CreateHouseResponse.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static CreateHouseResponse fail(String message) {
        return CreateHouseResponse.builder()
                .result(false)
                .message(message)
                .build();
    }
}
