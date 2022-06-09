package com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.RequestModel;

public interface RequestConverter<R extends RequestModel, I extends Input> {
    I toInput(R request);
}
