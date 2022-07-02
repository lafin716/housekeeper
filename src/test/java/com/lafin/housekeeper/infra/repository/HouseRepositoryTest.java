package com.lafin.housekeeper.infra.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HouseRepositoryTest {

    @Autowired
    private HouseRepository houseRepository;

    @Test
    public void 페이징테스트() {
        var userId = 1L;
        var paging = PageRequest.of(1, 10);

        var result = houseRepository.findByUserId(userId, paging);
        System.out.println(result);
    }
}