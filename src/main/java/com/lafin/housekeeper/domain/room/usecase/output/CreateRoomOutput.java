package com.lafin.housekeeper.domain.room.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateRoomOutput implements Output {

    private boolean result;

    private String message;

    public static CreateRoomOutput ok(String message) {
        return CreateRoomOutput.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static CreateRoomOutput fail(String message) {
        return CreateRoomOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
