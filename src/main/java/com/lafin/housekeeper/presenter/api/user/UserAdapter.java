package com.lafin.housekeeper.presenter.api.user;

import com.lafin.housekeeper.domain.user.interactor.CreateTokenInteractor;
import com.lafin.housekeeper.domain.user.interactor.CreateUserInteractor;
import com.lafin.housekeeper.domain.user.interactor.SignInUserInteractor;
import com.lafin.housekeeper.domain.user.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.user.usecase.input.SignInInput;
import com.lafin.housekeeper.presenter.api.user.request.CreateUserRequest;
import com.lafin.housekeeper.presenter.api.user.request.SignInRequest;
import com.lafin.housekeeper.presenter.api.user.request.convert.CreateUserRequestConverter;
import com.lafin.housekeeper.presenter.api.user.response.CreateUserResponse;
import com.lafin.housekeeper.presenter.api.user.response.SignInResponse;
import com.lafin.housekeeper.presenter.api.user.response.convert.CreateUserResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdapter {

    private final CreateUserInteractor createUserInteractor;

    private final SignInUserInteractor signInUserInteractor;

    private final CreateTokenInteractor createTokenInteractor;

    public CreateUserResponse create(CreateUserRequest request) throws InvalidInputException {
        var input = CreateUserRequestConverter.to(request);
        var output = createUserInteractor.execute(input);

        return CreateUserResponseConverter.from(output);
    }

    public SignInResponse signIn(SignInRequest request) throws InvalidInputException {
        var signInResult = signInUserInteractor.execute(SignInInput.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build());
        if (!signInResult.isResult()) {
            return SignInResponse.builder()
                    .result(false)
                    .message(signInResult.getMessage())
                    .build();
        }

        var tokenResult = createTokenInteractor.execute(CreateTokenInput.builder()
                        .id(signInResult.getUser().getId())
                        .email(signInResult.getUser().getEmail())
                        .build());
        if (!tokenResult.isResult()) {
            return SignInResponse.builder()
                    .result(false)
                    .message(tokenResult.getMessage())
                    .build();
        }

        return SignInResponse.builder()
                .result(true)
                .message(tokenResult.getMessage())
                .type(signInResult.getUser().getType())
                .accessToken(tokenResult.getAccessToken())
                .refreshToken(tokenResult.getRefreshToken())
                .createdAt(tokenResult.getCreatedAt())
                .build();
    }
}
