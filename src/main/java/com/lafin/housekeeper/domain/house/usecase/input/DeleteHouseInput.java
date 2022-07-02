package com.lafin.housekeeper.domain.house.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.util.NumberUtils;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Getter
@Builder
public class DeleteHouseInput implements Input {
    
    private Long userId;
    
    private Long houseId;

    @Override
    public boolean validate() throws InvalidInputException {
        if (NumberUtils.isNegative(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (NumberUtils.isNegative(houseId)) throw new InvalidInputException("집 고유번호가 없습니다.");

        return true;
    }
}
