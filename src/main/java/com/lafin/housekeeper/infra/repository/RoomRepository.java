package com.lafin.housekeeper.infra.repository;

import com.lafin.housekeeper.infra.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    RoomEntity findTopByUserIdAndHouseIdAndName(Long userId, Long houseId, String name);
}
