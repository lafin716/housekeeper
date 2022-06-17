package com.lafin.housekeeper.domain.house.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Builder
public class GetListHouseInput implements Input {

    private int page;

    private int block;

    private String accessToken;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Strings.isBlank(accessToken)) throw new InvalidInputException("토큰 정보가 없습니다.");
        if (page <= 0) {
            page = 1;
        }
        if (block <= 0) {
            block = 10;
        }

        return true;
    }
}
