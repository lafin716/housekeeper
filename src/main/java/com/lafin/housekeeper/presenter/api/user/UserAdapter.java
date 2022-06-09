package com.lafin.housekeeper.presenter.api.user;

import com.lafin.housekeeper.application.user.interactor.CreateUserInteractor;
import com.lafin.housekeeper.presenter.api.user.request.CreateUserRequest;
import com.lafin.housekeeper.presenter.api.user.request.convert.CreateUserRequestConverter;
import com.lafin.housekeeper.presenter.api.user.response.CreateUserResponse;
import com.lafin.housekeeper.presenter.api.user.response.convert.CreateUserResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdapter {

    private final CreateUserInteractor createUserInteractor;

    public CreateUserResponse create(CreateUserRequest request) throws InvalidInputException {
        var input = CreateUserRequestConverter.to(request);
        var output = createUserInteractor.execute(input);

        return CreateUserResponseConverter.from(output);
    }
}
