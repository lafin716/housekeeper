package com.lafin.housekeeper.infra.user.adapter;

import com.lafin.housekeeper.domain.user.gateway.AuthGateway;
import com.lafin.housekeeper.domain.user.model.Auth;
import com.lafin.housekeeper.infra.user.entity.convert.AuthEntityConverter;
import com.lafin.housekeeper.infra.user.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthGatewayAdapter implements AuthGateway {

    private final AuthRepository repository;

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
