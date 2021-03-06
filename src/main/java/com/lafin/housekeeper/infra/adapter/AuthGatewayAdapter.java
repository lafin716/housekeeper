package com.lafin.housekeeper.infra.adapter;

import com.lafin.housekeeper.domain.user.gateway.AuthGateway;
import com.lafin.housekeeper.domain.user.model.Auth;
import com.lafin.housekeeper.infra.entity.convert.AuthEntityConverter;
import com.lafin.housekeeper.infra.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthGatewayAdapter implements AuthGateway {

    private final AuthRepository repository;

    @Override
    public Auth findByAccessToken(String accessToken) {
        var entity = repository.findByAccessToken(accessToken);
        return AuthEntityConverter.to(entity);
    }

    @Override
    public Auth findByRefreshToken(String refreshToken) {
        var entity = repository.findByRefreshToken(refreshToken);
        return AuthEntityConverter.to(entity);
    }

    @Override
    public Auth findById(Long id) {
        return AuthEntityConverter.to(repository.findById(id).orElse(null));
    }

    @Override
    public List<Auth> findAll() {
        return repository.findAll()
                .stream()
                .map(AuthEntityConverter::to)
                .collect(Collectors.toList());
    }

    @Override
    public Auth save(Auth model) {
        var entity = AuthEntityConverter.from(model);
        return AuthEntityConverter.to(repository.save(entity));
    }

    @Override
    public boolean remove(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
