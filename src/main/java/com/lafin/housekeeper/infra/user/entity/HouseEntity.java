package com.lafin.housekeeper.infra.user.entity;

import com.lafin.housekeeper.shared.contract.infra.GatewayEntity;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hk_house")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HouseEntity implements GatewayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    @Enumerated(EnumType.STRING)
    private HouseType type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
