package com.lafin.housekeeper.shared.type;

public class UnknownTypeException extends RuntimeException {

    public UnknownTypeException() {
        super("알 수 없는 타입입니다.");
    }

    public UnknownTypeException(String message) {
        super(message);
    }
}
