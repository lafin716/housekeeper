package com.lafin.housekeeper.domain.room.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

@Getter
@Builder
public class CreateRoomInput implements Input {

    private Long userId;

    private Long houseId;

    private String name;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Objects.isNull(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (userId <= 0L) throw new InvalidInputException("잘못된 유저 고유번호입니다.");
        if (Objects.isNull(houseId)) throw new InvalidInputException("건물 고유번호가 없습니다.");
        if (houseId <= 0L) throw new InvalidInputException("잘못된 고유번호입니다.");
        if (Strings.isBlank(name)) throw new InvalidInputException("방 이름이 없습니다.");

        return true;
    }
}
