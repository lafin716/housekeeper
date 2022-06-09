package com.lafin.housekeeper.application.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.CreateUserUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.CreateUserInput;
import com.lafin.housekeeper.domain.user.usecase.output.CreateUserOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserInteractor implements CreateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public CreateUserOutput execute(CreateUserInput input) throws InvalidInputException {
        input.validate();

        var user = User.builder()
                    .email(input.getEmail())
                    .password(input.getPassword())
                    .nickName(input.getNickName())
                    .type(input.getType())
                    .platform(input.getPlatform())
                    .build();

        user.created();
        userGateway.save(user);

        return CreateUserOutput.builder()
                .result(true)
                .message("회원가입이 완료되었습니다.")
                .build();
    }
}
