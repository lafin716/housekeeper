package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.user.usecase.CreateTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.CreateUserUseCase;
import com.lafin.housekeeper.domain.user.usecase.SignInUseCase;
import com.lafin.housekeeper.domain.user.usecase.VerifyTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.user.usecase.input.SignInInput;
import com.lafin.housekeeper.domain.user.usecase.input.VerifyTokenInput;
import com.lafin.housekeeper.presenter.api.request.CreateUserRequest;
import com.lafin.housekeeper.presenter.api.request.SignInRequest;
import com.lafin.housekeeper.presenter.api.request.convert.CreateUserRequestConverter;
import com.lafin.housekeeper.presenter.api.response.CreateUserResponse;
import com.lafin.housekeeper.presenter.api.response.SignInResponse;
import com.lafin.housekeeper.presenter.api.response.convert.CreateUserResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdapter {

    private final VerifyTokenUseCase verifyTokenUseCase;

    private final CreateUserUseCase createUserUseCase;

    private final SignInUseCase signInUseCase;

    private final CreateTokenUseCase createTokenUseCase;

    public CreateUserResponse create(CreateUserRequest request) throws InvalidInputException {
        var input = CreateUserRequestConverter.to(request);
        var output = createUserUseCase.execute(input);

        return CreateUserResponseConverter.from(output);
    }

    public SignInResponse signIn(SignInRequest request) throws InvalidInputException {
        var signInResult = signInUseCase.execute(SignInInput.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build());
        if (!signInResult.isResult()) {
            return SignInResponse.builder()
                    .result(false)
                    .message(signInResult.getMessage())
                    .build();
        }

        var tokenResult = createTokenUseCase.execute(CreateTokenInput.builder()
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

    public Long verify(String accessToken) throws InvalidInputException {
        var verifyResult = verifyTokenUseCase.execute(VerifyTokenInput.builder()
                .accessToken(accessToken)
                .build());
        if (!verifyResult.isResult()) {
            throw new InvalidInputException(verifyResult.getMessage());
        }

        return verifyResult.getUser().getId();
    }
}
