package com.lafin.housekeeper.infra.repository;

import com.lafin.housekeeper.infra.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

    AuthEntity findByAccessToken(String accessToken);
    AuthEntity findByRefreshToken(String refreshToken);
}
