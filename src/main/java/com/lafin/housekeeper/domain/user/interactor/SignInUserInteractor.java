package com.lafin.housekeeper.domain.user.interactor;

import com.lafin.housekeeper.domain.user.gateway.UserGateway;
import com.lafin.housekeeper.domain.user.model.User;
import com.lafin.housekeeper.domain.user.usecase.SignInUseCase;
import com.lafin.housekeeper.domain.user.usecase.UpdateUserUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.SignInInput;
import com.lafin.housekeeper.domain.user.usecase.input.UpdateUserInput;
import com.lafin.housekeeper.domain.user.usecase.output.SignInOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.status.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SignInUserInteractor implements SignInUseCase {

    private final UserGateway userGateway;

    @Override
    public SignInOutput execute(SignInInput input) throws InvalidInputException {
        input.validate();

        var user = userGateway.findByEmail(input.getEmail());
        if (Objects.isNull(user)) {
            return SignInOutput.fail("회원 정보를 찾을 수 없습니다.");
        }

        int failCount = user.getFailedSignIn();
        userGateway.save(user);
        if (failCount >= user.getLimitFailSignIn()) {
            return SignInOutput.fail(messageOverFailSignIn(failCount, user.getLimitFailSignIn()));
        }

        if (!user.getPassword().equals(input.getPassword())) {
            user.failedSignIn();
            userGateway.save(user);
            return SignInOutput.fail("회원정보가 일치하지 않습니다. 다시 한번 확인해주세요.");
        }

        if (user.getStatus() == UserStatus.ON_REVIEW) {
            return SignInOutput.fail("승인되지 않은 회원입니다.");
        }

        if (user.getStatus() == UserStatus.BANNED) {
            return SignInOutput.fail("관리자에 의해 정지 된 회원입니다.");
        }

        if (user.getStatus() == UserStatus.DORMANT) {
            return SignInOutput.fail("휴면 회원입니다. 휴면해제 신청을 해주세요.");
        }

        if (user.getStatus() == UserStatus.LEFT) {
            return SignInOutput.fail("탈퇴 된 회원입니다.");
        }

        return SignInOutput.builder()
                .result(true)
                .message("로그인 완료")
                .user(user)
                .build();
    }

    private String messageOverFailSignIn(int failCount, int limitCount) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("로그인 횟수를 초과하였습니다. ");
        errorMessage.append(failCount);
        errorMessage.append("/");
        errorMessage.append(limitCount);

        return errorMessage.toString();
    }
}
