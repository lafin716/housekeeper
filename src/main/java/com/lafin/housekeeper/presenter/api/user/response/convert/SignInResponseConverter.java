package com.lafin.housekeeper.presenter.api.user.response.convert;

import com.lafin.housekeeper.domain.user.usecase.output.SignInOutput;
import com.lafin.housekeeper.presenter.api.user.response.SignInResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

public class SignInResponseConverter implements ResponseConverter<SignInResponse, SignInOutput> {

    @Override
    public SignInResponse fromOutput(SignInOutput output) {
        var builder = SignInResponse.builder()
                .result(output.isResult())
                .message(output.getMessage());

        if (output.isResult()) {
            builder.user(SignInResponse.User.builder()
                .email(output.getUser().getEmail())
                .nickName(output.getUser().getNickName())
                .type(output.getUser().getType().toString())
                .status(output.getUser().getStatus().toString())
                .created(output.getUser().getCreatedAt())
                .build());
        }

        return builder.build();
    }

    public static SignInResponse from(SignInOutput output) {
        var converter = new SignInResponseConverter();
        return converter.fromOutput(output);
    }
}
