package com.lafin.housekeeper.infra.user.entity.convert;

import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.infra.user.entity.UserEntity;
import com.lafin.housekeeper.shared.contract.infra.convert.GatewayConverter;
import com.lafin.housekeeper.shared.status.UserStatus;
import com.lafin.housekeeper.shared.type.UserType;

public class UserEntityConverter implements GatewayConverter<UserEntity, User> {

    @Override
    public UserEntity fromDomain(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .nickname(domain.getNickName())
                .type(domain.getType().toString())
                .status(domain.getStatus().toString())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    @Override
    public User toEntity(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .nickName(entity.getNickname())
                .type(UserType.of(entity.getType()))
                .status(UserStatus.valueOf(entity.getStatus()))
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