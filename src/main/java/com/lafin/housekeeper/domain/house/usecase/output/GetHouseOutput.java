package com.lafin.housekeeper.domain.house.usecase.output;

import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetHouseOutput implements Output {

    private boolean result;

    private String message;

    private House house;

    public static GetHouseOutput fail(String message) {
        return GetHouseOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
