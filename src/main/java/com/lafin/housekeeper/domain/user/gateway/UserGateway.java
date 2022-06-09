package com.lafin.housekeeper.domain.user.gateway;

import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

public interface UserGateway extends Repository<User, Long> {
}
