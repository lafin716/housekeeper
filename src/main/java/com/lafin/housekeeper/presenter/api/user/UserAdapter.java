package com.lafin.housekeeper.presenter.api.user;

import com.lafin.housekeeper.application.user.interactor.CreateUserInteractor;
import com.lafin.housekeeper.application.user.interactor.SignInUserInteractor;
import com.lafin.housekeeper.domain.user.usecase.input.SignInInput;
import com.lafin.housekeeper.presenter.api.user.request.CreateUserRequest;
import com.lafin.housekeeper.presenter.api.user.request.SignInRequest;
import com.lafin.housekeeper.presenter.api.user.request.convert.CreateUserRequestConverter;
import com.lafin.housekeeper.presenter.api.user.response.CreateUserResponse;
import com.lafin.housekeeper.presenter.api.user.response.SignInResponse;
import com.lafin.housekeeper.presenter.api.user.response.convert.CreateUserResponseConverter;
import com.lafin.housekeeper.presenter.api.user.response.convert.SignInResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdapter {

    private final CreateUserInteractor createUserInteractor;

    private final SignInUserInteractor signInUserInteractor;

    public CreateUserResponse create(CreateUserRequest request) throws InvalidInputException {
        var input = CreateUserRequestConverter.to(request);
        var output = createUserInteractor.execute(input);

        return CreateUserResponseConverter.from(output);
    }

    public SignInResponse signIn(SignInRequest request) throws InvalidInputException {
        var output = signInUserInteractor.execute(SignInInput.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build());
        return SignInResponseConverter.from(output);
    }
}
