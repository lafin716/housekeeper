package com.lafin.housekeeper.presenter.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Room> rooms;

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
}
