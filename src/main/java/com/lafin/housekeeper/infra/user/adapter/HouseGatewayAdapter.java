package com.lafin.housekeeper.infra.user.adapter;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.infra.user.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HouseGatewayAdapter implements HouseGateway {

    private final HouseRepository houseRepository;

    @Override
    public House findById(Long id) {
        return null;
    }

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }

    @Override
    public House save(House model) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
