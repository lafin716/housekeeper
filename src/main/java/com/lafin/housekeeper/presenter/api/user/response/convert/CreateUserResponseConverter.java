package com.lafin.housekeeper.presenter.api.user.response.convert;

import com.lafin.housekeeper.domain.user.usecase.output.CreateUserOutput;
import com.lafin.housekeeper.presenter.api.user.response.CreateUserResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

public class CreateUserResponseConverter implements ResponseConverter<CreateUserResponse, CreateUserOutput> {

    @Override
    public CreateUserResponse fromOutput(CreateUserOutput output) {
        return CreateUserResponse.builder()
                .result(output.isResult())
                .message(output.getMessage())
                .build();
    }

    public static CreateUserResponse from(CreateUserOutput output) {
        var converter = new CreateUserResponseConverter();
        return converter.fromOutput(output);
    }
}
