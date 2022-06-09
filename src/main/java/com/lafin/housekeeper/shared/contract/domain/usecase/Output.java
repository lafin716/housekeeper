package com.lafin.housekeeper.shared.contract.domain.usecase;

public interface Output {

    static Output ok() {
        return new Output() {
            final boolean result = true;
            final String message = "요청이 정상 수행되었습니다.";

            public boolean getResult() {
                return result;
            }

            public String getMessage() {
                return message;
            }
        };
    }

    static Output fail() {
        return new Output() {
            final boolean result = false;
            final String message = "요청이 정상적으로 수행되지 못하었습니다.";

            public boolean getResult() {
                return result;
            }

            public String getMessage() {
                return message;
            }
        };
    }
}
