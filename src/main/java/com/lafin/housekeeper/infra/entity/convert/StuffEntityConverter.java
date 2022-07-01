package com.lafin.housekeeper.infra.entity.convert;

import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.infra.entity.StuffEntity;
import com.lafin.housekeeper.shared.contract.infra.convert.GatewayConverter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StuffEntityConverter implements GatewayConverter<StuffEntity, Stuff> {

    @Override
    public StuffEntity fromDomain(Stuff domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        return StuffEntity.builder()
                .id(domain.getId())
                .userId(domain.getUserId())
                .roomId(domain.getRoomId())
                .name(domain.getName())
                .status(domain.getStatus())
                .amount(domain.getAmount())
                .unit(domain.getUnit())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    @Override
    public Stuff toEntity(StuffEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Stuff.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .roomId(entity.getRoomId())
                .name(entity.getName())
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .unit(entity.getUnit())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static StuffEntity from(Stuff domain) {
        var converter = new StuffEntityConverter();
        return converter.fromDomain(domain);
    }

    public static Stuff to(StuffEntity entity) {
        var converter = new StuffEntityConverter();
        return converter.toEntity(entity);
    }

    public static List<Stuff> toList(List<StuffEntity> entity) {
        return entity.stream()
                .map(StuffEntityConverter::to)
                .collect(Collectors.toList());
    }
}
