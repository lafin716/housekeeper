package com.lafin.housekeeper.presenter.api.user.response.convert;

import com.lafin.housekeeper.presenter.api.user.response.CreateUserResponse;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

public class CreateUserResponseConverter implements ResponseConverter<CreateUserResponse, DefaultOutput> {

    @Override
    public CreateUserResponse fromOutput(DefaultOutput output) {
        return CreateUserResponse.builder()
                .result(output.isResult())
                .message(output.getMessage())
                .build();
    }

    public static CreateUserResponse from(DefaultOutput output) {
        var converter = new CreateUserResponseConverter();
        return converter.fromOutput(output);
    }
}
