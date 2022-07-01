package com.lafin.housekeeper.infra.entity.convert;

import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.infra.entity.UserEntity;
import com.lafin.housekeeper.shared.contract.infra.convert.GatewayConverter;

import java.util.Objects;

public class UserEntityConverter implements GatewayConverter<UserEntity, User> {

    @Override
    public UserEntity fromDomain(User domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        return UserEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .nickname(domain.getNickName())
                .type(domain.getType())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    @Override
    public User toEntity(UserEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nickName(entity.getNickname())
                .type(entity.getType())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static UserEntity from(User domain) {
        var converter = new UserEntityConverter();
        return converter.fromDomain(domain);
    }

    public static User to(UserEntity entity) {
        var converter = new UserEntityConverter();
        return converter.toEntity(entity);
    }
}
