package com.lafin.housekeeper.domain.room.usecase.output;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateRoomOutput implements Output {

    private boolean result;

    private String message;

    private Room room;

    public static CreateRoomOutput ok(String message, Room room) {
        return CreateRoomOutput.builder()
                .result(true)
                .message(message)
                .room(room)
                .build();
    }

    public static CreateRoomOutput fail(String message) {
        return CreateRoomOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
