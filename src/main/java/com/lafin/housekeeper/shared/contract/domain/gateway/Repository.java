package com.lafin.housekeeper.shared.contract.domain.gateway;

import com.lafin.housekeeper.shared.contract.domain.model.Model;

import java.util.List;

public interface Repository<M extends Model, T> {
    M findById(T id);
    List<M> findAll();
    M save(M model);
    boolean remove(T id);
}
