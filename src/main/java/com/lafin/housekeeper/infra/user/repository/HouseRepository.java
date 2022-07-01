package com.lafin.housekeeper.infra.user.repository;

import com.lafin.housekeeper.infra.user.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {

    HouseEntity findTopByUserIdAndName(Long userId, String name);
}
