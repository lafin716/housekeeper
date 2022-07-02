package com.lafin.housekeeper.domain.house.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.util.NumberUtils;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetHouseInput implements Input {

    private Long userId;

    private Long houseId;

    @Override
    public boolean validate() throws InvalidInputException {
        if (NumberUtils.isNegative(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (NumberUtils.isNegative(houseId)) throw new InvalidInputException("건물 고유번호가 없습니다.");

        return true;
    }
}
