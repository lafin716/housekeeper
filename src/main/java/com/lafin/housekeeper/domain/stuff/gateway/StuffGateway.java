package com.lafin.housekeeper.domain.stuff.gateway;

import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.shared.contract.common.Paging;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

import java.util.List;

public interface StuffGateway extends Repository<Stuff, Long> {
    boolean isDuplicatedStuff(Long userId, Long roomId, String name);
    Stuff findByUserIdAndStuffId(Long userId, Long stuffId);
    List<Stuff> findAllByUserId(Long userId, Paging paging);
}
