package com.lafin.housekeeper.infra.entity.convert;

import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.infra.entity.HouseEntity;
import com.lafin.housekeeper.shared.contract.infra.convert.GatewayConverter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HouseEntityConverter implements GatewayConverter<HouseEntity, House> {


    @Override
    public HouseEntity fromDomain(House domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        return HouseEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .name(domain.getName())
                .type(domain.getType())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    @Override
    public House toEntity(HouseEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return House.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .name(entity.getName())
                .type(entity.getType())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static HouseEntity from(House domain) {
        var converter = new HouseEntityConverter();
        return converter.fromDomain(domain);
    }

    public static House to(HouseEntity entity) {
        var converter = new HouseEntityConverter();
        return converter.toEntity(entity);
    }

    public static List<House> toList(List<HouseEntity> entity) {
        return entity.stream()
                    .map(HouseEntityConverter::to)
                    .collect(Collectors.toList());
    }
}
