package com.lafin.housekeeper.domain.stuff.usecase.output;

import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetListStuffOutput implements Output {

    private boolean result;

    private String message;

    private List<Stuff> stuffs;

    public static GetListStuffOutput ok(List<Stuff> stuffs) {
        return GetListStuffOutput.builder()
                .result(true)
                .message("")
                .stuffs(stuffs)
                .build();
    }

    public static GetListStuffOutput fail(String message) {
        return GetListStuffOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
