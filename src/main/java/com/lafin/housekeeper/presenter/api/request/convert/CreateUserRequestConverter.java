package com.lafin.housekeeper.presenter.api.request.convert;

import com.lafin.housekeeper.domain.user.usecase.input.CreateUserInput;
import com.lafin.housekeeper.presenter.api.request.CreateUserRequest;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.RequestConverter;
import com.lafin.housekeeper.shared.type.PlatformType;
import com.lafin.housekeeper.shared.type.UserType;

public class CreateUserRequestConverter implements RequestConverter<CreateUserRequest, CreateUserInput> {

    @Override
    public CreateUserInput toInput(CreateUserRequest request) {
        return CreateUserInput.builder()
                .email(request.getEmail())
                .nickName(request.getNickName())
                .password(request.getPassword())
                .type(UserType.of(request.getType()))
                .platform(PlatformType.of(request.getPlatform()))
                .build();
    }

    public static CreateUserInput to(CreateUserRequest request) {
        var converter = new CreateUserRequestConverter();
        return converter.toInput(request);
    }
}
