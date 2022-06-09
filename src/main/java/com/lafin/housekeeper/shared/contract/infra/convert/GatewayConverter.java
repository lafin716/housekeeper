package com.lafin.housekeeper.shared.contract.infra.convert;

import com.lafin.housekeeper.shared.contract.domain.model.Model;
import com.lafin.housekeeper.shared.contract.infra.GatewayEntity;

public interface GatewayConverter<E extends GatewayEntity, D extends Model> {
    E fromDomain(D domain);
    D toEntity(E entity);
}
