package com.lafin.housekeeper.infra.entity;


import com.lafin.housekeeper.shared.contract.infra.GatewayEntity;
import com.lafin.housekeeper.shared.status.RoomStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hk_room")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RoomEntity implements GatewayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long houseId;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
