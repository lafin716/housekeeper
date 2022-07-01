package com.lafin.housekeeper.domain.stuff.gateway;

import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

public interface StuffGateway extends Repository<Stuff, Long> {

    boolean isDuplicatedStuff(Long userId, Long roomId, String name);
}
