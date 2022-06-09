package com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;

public interface ResponseConverter<R extends ResponseModel, O extends Output> {
    R fromOutput(O output);
}
