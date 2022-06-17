package com.lafin.housekeeper.infra.user.repository;

import com.lafin.housekeeper.infra.user.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

    AuthEntity findByEmailAndPassword(String email, String password);
}
