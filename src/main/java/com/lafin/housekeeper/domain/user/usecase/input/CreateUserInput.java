package com.lafin.housekeeper.domain.user.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.type.PlatformType;
import com.lafin.housekeeper.shared.type.UserType;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Base64Utils;

import java.util.Objects;

@Getter
@Builder
public class CreateUserInput implements Input {

    private UserType type;

    private PlatformType platform;

    private String email;

    private String nickName;

    private String password;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Strings.isBlank(email)) throw new InvalidInputException("이메일은 필수입니다.");
        if (Strings.isBlank(password)) throw new InvalidInputException("비밀번호는 필수입니다.");
        if (Strings.isBlank(nickName)) throw new InvalidInputException("닉네임은 필수입니다.");
        if (Objects.isNull(type)) throw new InvalidInputException("유저타입은 필수입니다.");
        if (type.isUnknown()) throw new InvalidInputException("알 수 없는 유저타입입니다.");
        if (Objects.isNull(platform)) throw new InvalidInputException("플랫폼 타입은 필수입니다.");
        if (platform.isUnknown()) throw new InvalidInputException("알 수 없는 플랫폼 타입입니다.");
        encryptPassword();

        return true;
    }

    private void encryptPassword() {
        if (Strings.isBlank(this.password)) return;
        this.password = Base64Utils.encodeToString(this.password.getBytes());
    }
}
