package com.lafin.housekeeper.domain.house.model;

import com.lafin.housekeeper.shared.contract.domain.model.Model;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
public class House implements Model {

    private Long id;

    private Long userId;

    private String name;

    private HouseType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
