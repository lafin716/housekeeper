package com.lafin.housekeeper.infra.repository;

import com.lafin.housekeeper.infra.entity.StuffEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StuffRepository extends JpaRepository<StuffEntity, Long> {
    StuffEntity findTopByUserIdAndRoomIdAndName(Long userId, Long roomId, String name);
    StuffEntity findTopByUserIdAndRoomIdAndIdNotAndName(Long userId, Long roomId, Long stuffId, String name);
    StuffEntity findTopByUserIdAndId(Long userId, Long stuffId);
    StuffEntity findTopByUserIdAndRoomId(Long userId, Long roomId);
    List<StuffEntity> findByUserId(Long userId, Pageable paging);
}
