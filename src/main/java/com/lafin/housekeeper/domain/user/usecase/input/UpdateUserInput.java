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
public class UpdateUserInput implements Input {

    private Long id;

    private String nickName;

    private String password;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Objects.isNull(id)) throw new InvalidInputException("ID는 필수입니다.");
        encryptPassword();

        return true;
    }

    private void encryptPassword() {
        if (Strings.isBlank(this.password)) return;
        this.password = Base64Utils.encodeToString(this.password.getBytes());
    }
}
