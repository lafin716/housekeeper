package com.lafin.housekeeper.presenter.api.house.response.convert;

import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.domain.house.usecase.output.GetListHouseOutput;
import com.lafin.housekeeper.presenter.api.house.response.GetListHouseResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

import java.util.stream.Collectors;

public class GetListHouseResponseConverter implements ResponseConverter<GetListHouseResponse, GetListHouseOutput> {

    @Override
    public GetListHouseResponse fromOutput(GetListHouseOutput output) {
        if (!output.isResult()) {
            return GetListHouseResponse.builder()
                    .result(false)
                    .message(output.getMessage())
                    .build();
        }
        return GetListHouseResponse.builder()
                .result(true)
                .houses(output.getHouses()
                        .stream()
                        .map(GetListHouseResponseConverter::toHouse)
                        .collect(Collectors.toList()))
                .build();
    }

    private static GetListHouseResponse.House toHouse(House house) {
        return GetListHouseResponse.House.builder()
                .id(house.getId())
                .name(house.getName())
                .type(house.getType())
                .createdAt(house.getCreatedAt())
                .build();
    }

    public static GetListHouseResponse from(GetListHouseOutput output) {
        var converter = new GetListHouseResponseConverter();
        return converter.fromOutput(output);
    }
}
