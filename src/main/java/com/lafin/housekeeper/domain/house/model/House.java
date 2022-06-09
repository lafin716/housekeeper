package com.lafin.housekeeper.domain.house.model;

import com.lafin.housekeeper.shared.type.HouseType;

import java.time.LocalDateTime;

public class House {

    private Long id;

    private String name;

    private HouseType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
