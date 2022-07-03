package com.lafin.housekeeper.infra.adapter;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.infra.repository.HouseRepository;
import com.lafin.housekeeper.infra.entity.convert.HouseEntityConverter;
import com.lafin.housekeeper.infra.repository.RoomRepository;
import com.lafin.housekeeper.shared.contract.common.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HouseGatewayAdapter implements HouseGateway {

    private final HouseRepository houseRepository;

    private final RoomRepository roomRepository;

    @Override
    public boolean isDuplicatedHouse(Long userId, String name) {
        var entity = houseRepository.findTopByUserIdAndName(userId, name);
        return Objects.nonNull(entity);
    }

    @Override
    public boolean isDuplicatedHouse(Long userId, Long houseId, String name) {
        var entity = houseRepository.findTopByUserIdAndIdNotAndName(userId, houseId, name);
        return Objects.nonNull(entity);
    }

    @Override
    public House findByUserIdAndHouseId(Long userId, Long houseId) {
        var entity = houseRepository.findTopByUserIdAndId(userId, houseId);
        return HouseEntityConverter.to(entity);
    }

    @Override
    public List<House> findAllByUserId(Long userId, Paging paging) {
        var page = PageRequest.of(paging.getPage() - 1, paging.getBlock());
        var houses = houseRepository.findByUserId(userId, page);
        return houses.stream()
                    .map(HouseEntityConverter::to)
                    .collect(Collectors.toList());
    }

    @Override
    public boolean hasRooms(Long userId, Long houseId) {
        var rooms = roomRepository.findTopByUserIdAndHouseId(userId, houseId);
        return Objects.nonNull(rooms);
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
