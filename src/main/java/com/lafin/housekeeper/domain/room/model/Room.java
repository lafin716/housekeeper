package com.lafin.housekeeper.domain.room.model;

import com.lafin.housekeeper.shared.contract.domain.model.Model;
import com.lafin.housekeeper.shared.status.RoomStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class Room implements Model {

    private Long id;

    private Long userId;

    private Long houseId;

    private RoomStatus status;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void created() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = RoomStatus.ACTIVE;
    }

    public void updateName(String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    public void deleted() {
        this.updatedAt = LocalDateTime.now();
        this.status = RoomStatus.DELETED;
    }

}
