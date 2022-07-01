package com.lafin.housekeeper.infra.entity.convert;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.infra.entity.RoomEntity;
import com.lafin.housekeeper.shared.contract.infra.convert.GatewayConverter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RoomEntityConverter implements GatewayConverter<RoomEntity, Room> {

    @Override
    public RoomEntity fromDomain(Room domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        return RoomEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .houseId(domain.getHouseId())
                .name(domain.getName())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    @Override
    public Room toEntity(RoomEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Room.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .houseId(entity.getHouseId())
                .name(entity.getName())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static RoomEntity from(Room domain) {
        var converter = new RoomEntityConverter();
        return converter.fromDomain(domain);
    }

    public static Room to(RoomEntity entity) {
        var converter = new RoomEntityConverter();
        return converter.toEntity(entity);
    }

    public static List<Room> toList(List<RoomEntity> entity) {
        return entity.stream()
                .map(RoomEntityConverter::to)
                .collect(Collectors.toList());
    }
}
