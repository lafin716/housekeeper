package com.lafin.housekeeper.infra.user.repository;

import com.lafin.housekeeper.infra.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
