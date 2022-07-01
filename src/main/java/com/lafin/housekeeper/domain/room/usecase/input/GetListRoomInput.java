package com.lafin.housekeeper.domain.room.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetListRoomInput implements Input {

    private int page;

    private int block;

    @Override
    public boolean validate() throws InvalidInputException {
        if (page <= 0) {
            page = 1;
        }
        if (block <= 0) {
            block = 10;
        }

        return true;
    }
}
