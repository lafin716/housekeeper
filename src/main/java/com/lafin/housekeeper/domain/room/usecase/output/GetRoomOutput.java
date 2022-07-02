package com.lafin.housekeeper.domain.room.usecase.output;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetRoomOutput implements Output {

    private boolean result;

    private String message;

    private Room room;

    public static GetRoomOutput fail(String message) {
        return GetRoomOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
