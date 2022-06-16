package com.lafin.housekeeper.domain.auth.gateway;

import com.lafin.housekeeper.domain.auth.model.Auth;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

public interface AuthRepository extends Repository<Auth, Long> {

    Auth findByEmailAndPassword(String email, String password);
}
