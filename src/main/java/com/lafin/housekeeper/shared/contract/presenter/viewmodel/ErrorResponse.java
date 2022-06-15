package com.lafin.housekeeper.shared.contract.presenter.viewmodel;

import lombok.Getter;

@Getter
public class ErrorResponse implements ResponseModel {

    private final boolean result = false;

    private final String message;

    private final String exception;

    public ErrorResponse(Exception exception) {
        this.message = exception.getMessage();
        this.exception = exception.getClass().getName();
    }
}
