package com.lafin.housekeeper.presenter.api.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import lombok.*;

@Builder
@Getter
public class CreateUserResponse implements ResponseModel {

    private boolean result;

    private String message;
}
