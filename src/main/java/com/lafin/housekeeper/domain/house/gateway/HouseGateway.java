package com.lafin.housekeeper.domain.house.gateway;

import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.shared.contract.common.Paging;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

import java.util.List;

public interface HouseGateway extends Repository<House, Long> {
    boolean isDuplicatedHouse(Long userId, String name);
    boolean isDuplicatedHouse(Long userId, Long houseId, String name);
    House findByUserIdAndHouseId(Long userId, Long houseId);
    List<House> findAllByUserId(Long userId, Paging paging);
    boolean hasRooms(Long userId, Long houseId);
}
