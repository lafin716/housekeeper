package com.lafin.housekeeper.infra.entity;

import com.lafin.housekeeper.shared.contract.infra.GatewayEntity;
import com.lafin.housekeeper.shared.status.RoomStatus;
import com.lafin.housekeeper.shared.status.StuffStatus;
import com.lafin.housekeeper.shared.type.Unit;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hk_stuff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StuffEntity implements GatewayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long roomId;

    @Enumerated(EnumType.STRING)
    private StuffStatus status;

    private String name;

    private float amount;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
