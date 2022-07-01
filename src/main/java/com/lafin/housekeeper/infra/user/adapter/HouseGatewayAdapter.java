package com.lafin.housekeeper.infra.user.adapter;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.infra.user.entity.convert.HouseEntityConverter;
import com.lafin.housekeeper.infra.user.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class HouseGatewayAdapter implements HouseGateway {

    private final HouseRepository houseRepository;

    @Override
    public boolean isDuplicatedHouse(Long userId, String name) {
        var entity = houseRepository.findTopByUserIdAndName(userId, name);
        return Objects.nonNull(entity);
    }

    @Override
    public House findById(Long id) {
        var entity = houseRepository.findById(id);
        return HouseEntityConverter.to(entity.orElse(null));
    }

    @Override
    public List<House> findAll() {
        return HouseEntityConverter.toList(houseRepository.findAll());
    }

    @Override
    public House save(House model) {
        var entity = HouseEntityConverter.from(model);
        return HouseEntityConverter.to(houseRepository.save(entity));
    }

    @Override
    public boolean remove(Long id) {
        try {
            houseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
