package com.lafin.housekeeper.infra.user.entity.convert;

import com.lafin.housekeeper.domain.user.model.Auth;
import com.lafin.housekeeper.infra.user.entity.AuthEntity;
import com.lafin.housekeeper.shared.contract.infra.convert.GatewayConverter;

import java.util.Objects;

public class AuthEntityConverter implements GatewayConverter<AuthEntity, Auth> {

    @Override
    public AuthEntity fromDomain(Auth domain) {
        if (Objects.isNull(domain)) {
            return null;
        }

        return AuthEntity.builder()
                .id(domain.getId())
                .accessToken(domain.getAccessToken())
                .refreshToken(domain.getRefreshToken())
                .createdAt(domain.getCreatedAt())
                .expiredAt(domain.getExpiredAt())
                .build();
    }

    @Override
    public Auth toEntity(AuthEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return Auth.builder()
                .id(entity.getId())
                .accessToken(entity.getAccessToken())
                .refreshToken(entity.getRefreshToken())
                .createdAt(entity.getCreatedAt())
                .expiredAt(entity.getExpiredAt())
                .build();
    }

    public static AuthEntity from(Auth domain) {
        var converter = new AuthEntityConverter();
        return converter.fromDomain(domain);
    }

    public static Auth to(AuthEntity entity) {
        var converter = new AuthEntityConverter();
        return converter.toEntity(entity);
    }
}
