package com.lafin.housekeeper.presenter.api.response.convert;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.domain.room.usecase.output.GetRoomOutput;
import com.lafin.housekeeper.presenter.api.response.GetRoomResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

public class GetRoomResponseConverter implements ResponseConverter<GetRoomResponse, GetRoomOutput> {

    @Override
    public GetRoomResponse fromOutput(GetRoomOutput output) {
        if (!output.isResult()) {
            return GetRoomResponse.builder()
                    .result(false)
                    .message(output.getMessage())
                    .build();
        }
        return GetRoomResponse.builder()
                .result(true)
                .room(toRoom(output.getRoom()))
                .build();
    }

    private static GetRoomResponse.Room toRoom(Room room) {
        return GetRoomResponse.Room.builder()
                .id(room.getId())
                .userId(room.getUserId())
                .houseId(room.getHouseId())
                .name(room.getName())
                .status(room.getStatus())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }

    public static GetRoomResponse from(GetRoomOutput output) {
        var converter = new GetRoomResponseConverter();
        return converter.fromOutput(output);
    }
}
