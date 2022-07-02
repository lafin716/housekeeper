package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.house.usecase.*;
import com.lafin.housekeeper.domain.house.usecase.input.*;
import com.lafin.housekeeper.presenter.api.request.CreateHouseRequest;
import com.lafin.housekeeper.presenter.api.request.UpdateHouseRequest;
import com.lafin.housekeeper.presenter.api.response.CreateHouseResponse;
import com.lafin.housekeeper.presenter.api.response.GetHouseResponse;
import com.lafin.housekeeper.presenter.api.response.GetListHouseResponse;
import com.lafin.housekeeper.presenter.api.response.convert.GetHouseResponseConverter;
import com.lafin.housekeeper.presenter.api.response.convert.GetListHouseResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.DefaultResponse;
import com.lafin.housekeeper.shared.contract.common.Paging;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HouseAdapter {
    private final GetListHouseUseCase getListHouseUseCase;
    private final GetHouseUseCase getHouseUseCase;
    private final CreateHouseUseCase createHouseUseCase;
    private final UpdateHouseUseCase updateHouseUseCase;
    private final DeleteHouseUseCase deleteHouseUseCase;

    public GetListHouseResponse getHouses(Long userId, Paging paging) throws InvalidInputException {
        var houseListResult = getListHouseUseCase.execute(GetListHouseInput.builder()
                        .userId(userId)
                        .paging(paging)
                        .build());

        return GetListHouseResponseConverter.from(houseListResult);
    }

    public GetHouseResponse getHouse(Long userId, Long houseId) throws InvalidInputException {
        var house = getHouseUseCase.execute(GetHouseInput.builder()
                        .userId(userId)
                        .houseId(houseId)
                        .build());

        return GetHouseResponseConverter.from(house);
    }

    public CreateHouseResponse addHouse(CreateHouseRequest request) throws InvalidInputException {
        var addResult = createHouseUseCase.execute(CreateHouseInput.builder()
                        .userId(request.getUserId())
                        .name(request.getName())
                        .type(HouseType.of(request.getType()))
                        .build());
        if (!addResult.isResult()) {
            return CreateHouseResponse.fail(addResult.getMessage());
        }

        return CreateHouseResponse.ok(addResult.getMessage());
    }

    public GetHouseResponse updateHouse(UpdateHouseRequest request) throws InvalidInputException {
        var result = updateHouseUseCase.execute(UpdateHouseInput.builder()
                .userId(request.getUserId())
                .houseId(request.getHouseId())
                .name(request.getName())
                .build());

        return GetHouseResponseConverter.from(result);
    }

    public DefaultResponse deleteHouse(Long userId, Long houseId) throws InvalidInputException {
        var result = deleteHouseUseCase.execute(DeleteHouseInput.builder()
                .userId(userId)
                .houseId(houseId)
                .build());

        return DefaultResponse.response(result);
    }

}
