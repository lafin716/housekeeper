package com.lafin.housekeeper.domain.room.gateway;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

public interface RoomGateway extends Repository<Room, Long>  {

    boolean isDuplicatedRoom(Long userId, Long houseId, String name);
}
