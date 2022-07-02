package com.lafin.housekeeper.presenter.api.response.convert;

import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetListStuffOutput;
import com.lafin.housekeeper.presenter.api.response.GetListStuffResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

import java.util.stream.Collectors;

public class GetListStuffResponseConverter implements ResponseConverter<GetListStuffResponse, GetListStuffOutput> {

    @Override
    public GetListStuffResponse fromOutput(GetListStuffOutput output) {
        if (!output.isResult()) {
            return GetListStuffResponse.builder()
                    .result(false)
                    .message(output.getMessage())
                    .build();
        }
        return GetListStuffResponse.builder()
                .result(true)
                .stuffs(output.getStuffs()
                        .stream()
                        .map(GetListStuffResponseConverter::toStuff)
                        .collect(Collectors.toList()))
                .build();
    }

    private static GetListStuffResponse.Stuff toStuff(Stuff stuff) {
        return GetListStuffResponse.Stuff.builder()
                .id(stuff.getId())
                .name(stuff.getName())
                .status(stuff.getStatus())
                .amount(stuff.getAmount())
                .unit(stuff.getUnit().getLabel())
                .createdAt(stuff.getCreatedAt())
                .updatedAt(stuff.getUpdatedAt())
                .build();
    }

    public static GetListStuffResponse from(GetListStuffOutput output) {
        var converter = new GetListStuffResponseConverter();
        return converter.fromOutput(output);
    }
}
