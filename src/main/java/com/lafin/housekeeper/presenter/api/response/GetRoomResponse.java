package com.lafin.housekeeper.presenter.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.status.RoomStatus;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class GetRoomResponse implements ResponseModel {

    private boolean result;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Room room;

    @Getter
    @Builder
    @ToString
    public static class Room {

        private Long id;

        private Long userId;

        private Long houseId;

        private RoomStatus status;

        private String name;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }

    public static GetRoomResponse ok(Room room) {
        return GetRoomResponse.builder()
                .result(true)
                .room(room)
                .build();
    }

    public static GetRoomResponse fail(String message) {
        return GetRoomResponse.builder()
                .result(true)
                .message(message)
                .build();
    }
}
