package com.lafin.housekeeper.infra.user.repository;

import com.lafin.housekeeper.domain.house.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
