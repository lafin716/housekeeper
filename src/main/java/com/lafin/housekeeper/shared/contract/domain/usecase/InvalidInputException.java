package com.lafin.housekeeper.shared.contract.domain.usecase;

public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
