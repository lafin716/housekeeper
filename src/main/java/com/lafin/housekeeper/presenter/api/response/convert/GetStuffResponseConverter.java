package com.lafin.housekeeper.presenter.api.response.convert;

import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetListStuffOutput;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetStuffOutput;
import com.lafin.housekeeper.presenter.api.response.GetListStuffResponse;
import com.lafin.housekeeper.presenter.api.response.GetStuffResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

import java.util.stream.Collectors;

public class GetStuffResponseConverter implements ResponseConverter<GetStuffResponse, GetStuffOutput> {

    @Override
    public GetStuffResponse fromOutput(GetStuffOutput output) {
        if (!output.isResult()) {
            return GetStuffResponse.builder()
                    .result(false)
                    .message(output.getMessage())
                    .build();
        }
        return GetStuffResponse.builder()
                .result(true)
                .stuff(toStuff(output.getStuff()))
                .build();
    }

    private static GetStuffResponse.Stuff toStuff(Stuff stuff) {
        return GetStuffResponse.Stuff.builder()
                .id(stuff.getId())
                .name(stuff.getName())
                .status(stuff.getStatus())
                .amount(stuff.getAmount())
                .unit(stuff.getUnit().getLabel())
                .createdAt(stuff.getCreatedAt())
                .updatedAt(stuff.getUpdatedAt())
                .build();
    }

    public static GetStuffResponse from(GetStuffOutput output) {
        var converter = new GetStuffResponseConverter();
        return converter.fromOutput(output);
    }
}
