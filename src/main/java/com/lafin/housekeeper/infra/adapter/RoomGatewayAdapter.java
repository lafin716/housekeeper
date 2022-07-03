package com.lafin.housekeeper.infra.adapter;

import com.lafin.housekeeper.domain.room.gateway.RoomGateway;
import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.infra.repository.RoomRepository;
import com.lafin.housekeeper.infra.entity.convert.RoomEntityConverter;
import com.lafin.housekeeper.infra.repository.StuffRepository;
import com.lafin.housekeeper.shared.contract.common.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoomGatewayAdapter implements RoomGateway {

    private final RoomRepository repository;
    private final StuffRepository stuffRepository;

    @Override
    public boolean isDuplicatedRoom(Long userId, Long houseId, String name) {
        var room = repository.findTopByUserIdAndHouseIdAndName(userId, houseId, name);
        return Objects.nonNull(room);
    }

    @Override
    public boolean isDuplicatedRoom(Long userId, Long houseId, Long roomId, String name) {
        var room = repository.findTopByUserIdAndHouseIdAndIdNotAndName(userId, houseId, roomId, name);
        return Objects.nonNull(room);
    }

    @Override
    public List<Room> findAllByUserId(Long userId, Paging paging) {
        var page = PageRequest.of(paging.getPage() - 1, paging.getBlock());
        return repository.findByUserId(userId, page)
                .stream()
                .map(RoomEntityConverter::to)
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasStuff(Long userId, Long roomId) {
        var stuff = stuffRepository.findTopByUserIdAndRoomId(userId, roomId);
        return Objects.nonNull(stuff);
    }

    @Override
    public Room findByUserIdAndRoomId(Long userId, Long roomId) {
        var room = repository.findTopByUserIdAndId(userId, roomId);
        return RoomEntityConverter.to(room);
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
