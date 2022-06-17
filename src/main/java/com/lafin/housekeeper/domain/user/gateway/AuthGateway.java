package com.lafin.housekeeper.domain.user.gateway;

import com.lafin.housekeeper.domain.user.model.Auth;
import com.lafin.housekeeper.shared.contract.domain.gateway.Repository;

public interface AuthGateway extends Repository<Auth, Long> {

    Auth findByAccessToken(String accessToken);
    Auth findByRefreshToken(String refreshToken);
}
