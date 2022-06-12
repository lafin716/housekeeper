package com.lafin.housekeeper.application.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.UpdateUserUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.UpdateUserInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateUserInteractor implements UpdateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public DefaultOutput execute(UpdateUserInput input) throws InvalidInputException {
        input.validate();

        var user = userGateway.findById(input.getId());
        if (Objects.isNull(user)) {
            return DefaultOutput.fail("회원 정보를 찾을 수 없습니다.");
        }

        user.updateInMyPage(input.getNickName(), input.getPassword());
        var savedUser = userGateway.save(user);
        if (!isSaved(savedUser)) {
            return DefaultOutput.fail("회원정보 수정이 실패하였습니다.");
        }

        return DefaultOutput.ok("회원 정보가 수정되었습니다.");
    }

    public boolean isSaved(User user) {
        if (Objects.isNull(user)) return false;
        if (Objects.isNull(user.getId())) return false;
        if (user.getId() <= 0) return false;

        return true;
    }
}
