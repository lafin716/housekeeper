package com.lafin.housekeeper.domain.stuff.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.util.NumberUtils;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FillStuffInput implements Input {

    private Long userId;

    private Long stuffId;

    private float fillAmount;

    @Override
    public boolean validate() throws InvalidInputException {
        if (NumberUtils.isNegative(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (NumberUtils.isNegative(stuffId)) throw new InvalidInputException("물건 고유번호가 없습니다.");
        if (NumberUtils.isNegative(fillAmount)) throw new InvalidInputException("충전량이 없습니다.");

        return true;
    }
}
