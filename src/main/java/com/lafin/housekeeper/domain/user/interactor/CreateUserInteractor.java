package com.lafin.housekeeper.domain.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.CreateUserUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.CreateUserInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateUserInteractor implements CreateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public DefaultOutput execute(CreateUserInput input) throws InvalidInputException {
        input.validate();

        var user = userGateway.findByEmail(input.getEmail());
        if (Objects.nonNull(user)) {
            return DefaultOutput.fail("이미 가입 된 이메일입니다.");
        }

        var newUser = User.builder()
                    .email(input.getEmail())
                    .password(input.getPassword())
                    .nickName(input.getNickName())
                    .type(input.getType())
                    .platform(input.getPlatform())
                    .build();
        newUser.created();

        var savedUser = userGateway.save(newUser);
        if (!isSaved(savedUser)) {
            return DefaultOutput.fail("회원가입이 실패하였습니다.");
        }

        return DefaultOutput.ok("회원가입이 완료되었습니다.");
    }

    public boolean isSaved(User user) {
        if (Objects.isNull(user)) return false;
        if (Objects.isNull(user.getId())) return false;
        if (user.getId() <= 0) return false;

        return true;
    }
}
