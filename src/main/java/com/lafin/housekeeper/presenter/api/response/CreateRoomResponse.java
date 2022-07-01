package com.lafin.housekeeper.presenter.api.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateRoomResponse implements ResponseModel {

    private boolean result;

    private String message;

    public static CreateRoomResponse ok(String message) {
        return CreateRoomResponse.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static CreateRoomResponse fail(String message) {
        return CreateRoomResponse.builder()
                .result(false)
                .message(message)
                .build();
    }
}
