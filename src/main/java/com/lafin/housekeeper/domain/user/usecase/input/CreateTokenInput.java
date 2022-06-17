package com.lafin.housekeeper.domain.user.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

@Getter
@Builder
@ToString
public class CreateTokenInput implements Input {

    private Long id;

    private String email;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Objects.isNull(id)) throw new InvalidInputException("고유번호는 필수입니다.");
        if (Strings.isBlank(email)) throw new InvalidInputException("이메일은 필수입니다.");

        return true;
    }
}
