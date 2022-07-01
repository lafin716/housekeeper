package com.lafin.housekeeper.infra.adapter;

import com.lafin.housekeeper.domain.room.gateway.RoomGateway;
import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.infra.repository.RoomRepository;
import com.lafin.housekeeper.infra.entity.convert.RoomEntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RoomGatewayAdapter implements RoomGateway {

    private final RoomRepository repository;

    @Override
    public boolean isDuplicatedRoom(Long userId, Long houseId, String name) {
        var room = repository.findTopByUserIdAndHouseIdAndName(userId, houseId, name);
        return Objects.nonNull(room);
    }

    @Override
    public Room findById(Long id) {
        var entity = repository.findById(id);
        return RoomEntityConverter.to(entity.orElse(null));
    }

    @Override
    public List<Room> findAll() {
        return RoomEntityConverter.toList(repository.findAll());
    }

    @Override
    public Room save(Room model) {
        var entity = RoomEntityConverter.from(model);
        return RoomEntityConverter.to(repository.save(entity));
    }

    @Override
    public boolean remove(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
