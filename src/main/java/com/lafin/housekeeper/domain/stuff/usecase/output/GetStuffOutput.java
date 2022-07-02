package com.lafin.housekeeper.domain.stuff.usecase.output;

import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetStuffOutput implements Output {

    private boolean result;

    private String message;

    private Stuff stuff;

    public static GetStuffOutput ok(Stuff stuff) {
        return GetStuffOutput.builder()
                .result(true)
                .message("")
                .stuff(stuff)
                .build();
    }

    public static GetStuffOutput fail(String message) {
        return GetStuffOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
