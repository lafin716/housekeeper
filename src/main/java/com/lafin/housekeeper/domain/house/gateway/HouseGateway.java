package com.lafin.housekeeper.domain.house.gateway;

import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

public interface HouseGateway extends Repository<House, Long> {

    boolean isDuplicatedHouse(Long userId, String name);

    House findByUserIdAndHouseId(Long userId, Long houseId);
}
