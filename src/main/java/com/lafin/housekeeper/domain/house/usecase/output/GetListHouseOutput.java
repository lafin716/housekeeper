package com.lafin.housekeeper.domain.house.usecase.output;

import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetListHouseOutput implements Output {

    private boolean result;

    private String message;

    private List<House> houses;

    public static GetListHouseOutput fail(String message) {
        return GetListHouseOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
