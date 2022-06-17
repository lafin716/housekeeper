package com.lafin.housekeeper.domain.user.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Builder
public class VerifyTokenInput implements Input {

    private String accessToken;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Strings.isBlank(accessToken)) throw new InvalidInputException("토큰이 없습니다.");

        return true;
    }
}
