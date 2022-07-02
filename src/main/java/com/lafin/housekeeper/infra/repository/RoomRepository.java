package com.lafin.housekeeper.infra.repository;

import com.lafin.housekeeper.infra.entity.RoomEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    RoomEntity findTopByUserIdAndHouseIdAndName(Long userId, Long houseId, String name);
    RoomEntity findTopByUserIdAndId(Long userId, Long roomId);
    RoomEntity findTopByUserIdAndHouseId(Long userId, Long houseId);
    List<RoomEntity> findByUserId(Long userId, Pageable paging);
}
