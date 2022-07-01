package com.lafin.housekeeper.domain.house.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.Builder;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

@Getter
@Builder
public class CreateHouseInput implements Input {

    private Long userId;

    private String name;

    private HouseType type;

    @Override
    public boolean validate() throws InvalidInputException {
        if (Objects.isNull(userId)) throw new InvalidInputException("유저 고유번호가 없습니다.");
        if (userId <= 0L) throw new InvalidInputException("잘못된 유저 고유번호입니다.");
        if (Strings.isBlank(name)) throw new InvalidInputException("건물 이름을 입력해주세요.");
        if (Objects.isNull(type)) throw new InvalidInputException("건물 타입을 선택해주세요.");
        
        return true;
    }
}
