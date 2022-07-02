package com.lafin.housekeeper.domain.stuff.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.util.NumberUtils;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpendStuffInput implements Input {

    private Long userId;

    private Long stuffId;
    
    private float useAmount;

    @Override
    public boolean validate() throws InvalidInputException {
        if (NumberUtils.isNegative(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (NumberUtils.isNegative(stuffId)) throw new InvalidInputException("물건 고유번호가 없습니다.");
        if (NumberUtils.isNegative(useAmount)) throw new InvalidInputException("사용량이 없습니다.");

        return true;
    }
}
