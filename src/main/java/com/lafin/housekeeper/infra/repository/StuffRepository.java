package com.lafin.housekeeper.infra.repository;

import com.lafin.housekeeper.infra.entity.StuffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffRepository extends JpaRepository<StuffEntity, Long> {

    StuffEntity findTopByUserIdAndRoomIdAndName(Long userId, Long roomId, String name);

    StuffEntity findTopByUserIdAndId(Long userId, Long stuffId);
}
