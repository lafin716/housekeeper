package com.lafin.housekeeper.infra.user.entity;

import com.lafin.housekeeper.shared.contract.infra.GatewayEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hk_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserEntity implements GatewayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String password;

    private String status;

    private String type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
