package com.lafin.housekeeper.domain.room.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.util.NumberUtils;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetRoomInput implements Input {

    private Long userId;

    private Long roomId;

    @Override
    public boolean validate() throws InvalidInputException {
        if (NumberUtils.isNegative(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (NumberUtils.isNegative(roomId)) throw new InvalidInputException("방 고유번호가 없습니다.");

        return true;
    }
}
