package com.lafin.housekeeper.presenter.api.response.convert;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.domain.room.usecase.output.GetListRoomOutput;
import com.lafin.housekeeper.presenter.api.response.GetListRoomResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

import java.util.stream.Collectors;

public class GetListRoomResponseConverter implements ResponseConverter<GetListRoomResponse, GetListRoomOutput> {

    @Override
    public GetListRoomResponse fromOutput(GetListRoomOutput output) {
        if (!output.isResult()) {
            return GetListRoomResponse.builder()
                    .result(false)
                    .message(output.getMessage())
                    .build();
        }
        return GetListRoomResponse.builder()
                .result(true)
                .rooms(output.getRooms()
                        .stream()
                        .map(GetListRoomResponseConverter::toRoom)
                        .collect(Collectors.toList()))
                .build();
    }

    private static GetListRoomResponse.Room toRoom(Room room) {
        return GetListRoomResponse.Room.builder()
                .id(room.getId())
                .houseId(room.getHouseId())
                .name(room.getName())
                .status(room.getStatus())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }

    public static GetListRoomResponse from(GetListRoomOutput output) {
        var converter = new GetListRoomResponseConverter();
        return converter.fromOutput(output);
    }
}
