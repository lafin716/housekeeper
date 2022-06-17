package com.lafin.housekeeper.infra.user.repository;

import com.lafin.housekeeper.infra.user.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
}
