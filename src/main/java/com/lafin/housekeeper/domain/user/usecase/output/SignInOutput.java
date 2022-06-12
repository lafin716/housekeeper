package com.lafin.housekeeper.domain.user.usecase.output;

import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignInOutput implements Output {

    private boolean result;

    private String message;

    public User user;

    public static SignInOutput fail(String message) {
        return SignInOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
