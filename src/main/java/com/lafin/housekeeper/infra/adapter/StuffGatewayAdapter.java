package com.lafin.housekeeper.infra.adapter;

import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.domain.stuff.gateway.StuffGateway;
import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.infra.entity.convert.RoomEntityConverter;
import com.lafin.housekeeper.infra.entity.convert.StuffEntityConverter;
import com.lafin.housekeeper.infra.repository.StuffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StuffGatewayAdapter implements StuffGateway {

    private final StuffRepository repository;

    @Override
    public boolean isDuplicatedStuff(Long userId, Long roomId, String name) {
        var stuff = repository.findTopByUserIdAndRoomIdAndName(userId, roomId, name);
        return Objects.nonNull(stuff);
    }

    @Override
    public Stuff findByUserIdAndStuffId(Long userId, Long stuffId) {
        var stuff = repository.findTopByUserIdAndId(userId, stuffId);
        return StuffEntityConverter.to(stuff);
    }

    @Override
    public Stuff findById(Long id) {
        var entity = repository.findById(id);
        return StuffEntityConverter.to(entity.orElse(null));
    }

    @Override
    public List<Stuff> findAll() {
        return StuffEntityConverter.toList(repository.findAll());
    }

    @Override
    public Stuff save(Stuff model) {
        var entity = StuffEntityConverter.from(model);
        return StuffEntityConverter.to(repository.save(entity));
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
