package com.lafin.housekeeper.domain.stuff.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.type.Unit;
import com.lafin.housekeeper.shared.util.NumberUtils;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

@Getter
@Builder
public class CreateStuffInput implements Input {

    private Long userId;

    private Long roomId;

    private String name;

    private float amount;

    private Unit unit;

    @Override
    public boolean validate() throws InvalidInputException {
        if (NumberUtils.isNegative(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (NumberUtils.isNegative(roomId)) throw new InvalidInputException("방 고유번호가 없습니다.");
        if (Strings.isBlank(name)) throw new InvalidInputException("이름을 입력하세요.");
        if (NumberUtils.isNegative(amount)) throw new InvalidInputException("수량을 입력하세요.");
        if (Objects.isNull(unit)) throw new InvalidInputException("단위를 입력하세요.");
        
        return true;
    }
}
