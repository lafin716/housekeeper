package com.lafin.housekeeper.infra.user.entity;

import com.lafin.housekeeper.shared.contract.infra.GatewayEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
