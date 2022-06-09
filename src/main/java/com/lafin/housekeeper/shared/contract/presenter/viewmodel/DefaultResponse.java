package com.lafin.housekeeper.shared.contract.presenter.viewmodel;

import lombok.Getter;

@Getter
public class DefaultResponse implements ResponseModel {

    private final boolean result = false;

    private final String message;

    public DefaultResponse(String message) {
        this.message = message;
    }
}
