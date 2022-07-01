package com.lafin.housekeeper.infra.entity;

import com.lafin.housekeeper.shared.contract.infra.GatewayEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hk_auth")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthEntity implements GatewayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;

    private String refreshToken;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;
}
