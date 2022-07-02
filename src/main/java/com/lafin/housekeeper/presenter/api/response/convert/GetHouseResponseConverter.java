package com.lafin.housekeeper.presenter.api.response.convert;

import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.domain.house.usecase.output.GetHouseOutput;
import com.lafin.housekeeper.presenter.api.response.GetHouseResponse;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.ResponseConverter;

public class GetHouseResponseConverter implements ResponseConverter<GetHouseResponse, GetHouseOutput> {

    @Override
    public GetHouseResponse fromOutput(GetHouseOutput output) {
        if (!output.isResult()) {
            return GetHouseResponse.builder()
                    .result(false)
                    .message(output.getMessage())
                    .build();
        }
        return GetHouseResponse.builder()
                .result(true)
                .house(toHouse(output.getHouse()))
                .build();
    }

    private static GetHouseResponse.House toHouse(House house) {
        return GetHouseResponse.House.builder()
                .id(house.getId())
                .userId(house.getUserId())
                .name(house.getName())
                .type(house.getType())
                .createdAt(house.getCreatedAt())
                .build();
    }

    public static GetHouseResponse from(GetHouseOutput output) {
        var converter = new GetHouseResponseConverter();
        return converter.fromOutput(output);
    }
}
