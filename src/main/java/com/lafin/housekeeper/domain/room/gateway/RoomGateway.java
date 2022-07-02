package com.lafin.housekeeper.domain.room.gateway;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.shared.contract.common.Paging;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

import java.util.List;

public interface RoomGateway extends Repository<Room, Long>  {
    boolean isDuplicatedRoom(Long userId, Long houseId, String name);
    Room findByUserIdAndRoomId(Long userId, Long roomId);
    List<Room> findAllByUserId(Long userId, Paging paging);
    boolean hasStuff(Long userId, Long roomId);
}
