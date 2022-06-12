package com.lafin.housekeeper.application.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.CreateUserUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.CreateUserInput;
import com.lafin.housekeeper.domain.user.usecase.output.CreateUserOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateUserInteractor implements CreateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public CreateUserOutput execute(CreateUserInput input) throws InvalidInputException {
        input.validate();

        // 비밀번호 암호화
        var encryptedPassword = Base64Utils.encodeToString(input.getPassword().getBytes());

        var user = User.builder()
                    .email(input.getEmail())
                    .password(encryptedPassword)
                    .nickName(input.getNickName())
                    .type(input.getType())
                    .platform(input.getPlatform())
                    .build();
        user.created();

        var savedUser = userGateway.save(user);
        if (!isSaved(savedUser)) {
            return CreateUserOutput.builder()
                    .result(false)
                    .message("회원가입이 실패하였습니다.")
                    .build();
        }

        return CreateUserOutput.builder()
                .result(true)
                .message("회원가입이 완료되었습니다.")
                .build();
    }

    public boolean isSaved(User user) {
        if (Objects.isNull(user)) return false;
        if (Objects.isNull(user.getId())) return false;
        if (user.getId() <= 0) return false;

        return true;
    }
}
