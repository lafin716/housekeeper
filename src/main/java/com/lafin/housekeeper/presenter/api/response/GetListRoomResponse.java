package com.lafin.housekeeper.presenter.api.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.status.RoomStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
public class GetListRoomResponse implements ResponseModel {

    private boolean result;

    private String message;

    private List<Room> rooms;

    @Getter
    @Builder
    @ToString
    public static class Room {

        private Long id;

        private Long houseId;

        private String name;

        private RoomStatus status;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
