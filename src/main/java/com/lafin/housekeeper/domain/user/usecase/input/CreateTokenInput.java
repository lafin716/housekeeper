package com.lafin.housekeeper.domain.user.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Base64Utils;

@Getter
@Builder
@ToString
public class CreateTokenInput implements Input {

    private String email;

    private String password;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Strings.isBlank(email)) throw new InvalidInputException("이메일은 필수입니다.");
        if (Strings.isBlank(password)) throw new InvalidInputException("비밀번호는 필수입니다.");
        encryptPassword();

        return true;
    }

    private void encryptPassword() {
        if (Strings.isBlank(this.password)) return;
        this.password = Base64Utils.encodeToString(this.password.getBytes());
    }
}
