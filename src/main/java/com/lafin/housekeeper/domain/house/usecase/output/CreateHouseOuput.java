package com.lafin.housekeeper.domain.house.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateHouseOuput implements Output {

    private boolean result;

    private String message;

    public static CreateHouseOuput fail(String message) {
        return CreateHouseOuput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
