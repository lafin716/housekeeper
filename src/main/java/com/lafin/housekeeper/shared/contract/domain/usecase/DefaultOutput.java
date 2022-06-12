package com.lafin.housekeeper.shared.contract.domain.usecase;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DefaultOutput implements Output {

    private boolean result;

    private String message;
    
    public static DefaultOutput ok() {
        return ok("성공하였습니다.");
    }

    public static DefaultOutput ok(String message) {
        return DefaultOutput.builder()
                .result(true)
                .message(message)
                .build();
    }

    public static DefaultOutput fail() {
        return fail("실패하였습니다.");
    }

    public static DefaultOutput fail(String message) {
        return DefaultOutput.builder()
                .result(true)
                .message(message)
                .build();
    }
}
