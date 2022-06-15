package com.lafin.housekeeper.infra.user.adapter;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.infra.user.entity.convert.UserEntityConverter;
import com.lafin.housekeeper.infra.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserGatewayAdapter implements UserGateway {

    private final UserRepository repository;

    @Override
    public User findByEmail(String email) {
        var entity = repository.findByEmail(email);
        return UserEntityConverter.to(entity);
    }

    @Override
    public User findById(Long id) {
        var entity = repository.findById(id).orElse(null);
        return UserEntityConverter.to(entity);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll()
                        .stream()
                        .map(UserEntityConverter::to)
                        .collect(Collectors.toList());
    }

    @Override
    public User save(User model) {
        var entity = UserEntityConverter.from(model);
        var savedEntity = repository.save(entity);
        return UserEntityConverter.to(savedEntity);
    }

    @Override
    public boolean remove(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
