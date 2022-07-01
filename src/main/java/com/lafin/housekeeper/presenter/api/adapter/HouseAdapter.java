package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.house.usecase.CreateHouseUseCase;
import com.lafin.housekeeper.domain.house.usecase.GetListHouseUseCase;
import com.lafin.housekeeper.domain.house.usecase.input.CreateHouseInput;
import com.lafin.housekeeper.domain.house.usecase.input.GetListHouseInput;
import com.lafin.housekeeper.domain.user.usecase.VerifyTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.VerifyTokenInput;
import com.lafin.housekeeper.presenter.api.request.CreateHouseRequest;
import com.lafin.housekeeper.presenter.api.response.CreateHouseResponse;
import com.lafin.housekeeper.presenter.api.response.GetListHouseResponse;
import com.lafin.housekeeper.presenter.api.response.convert.GetListHouseResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.Paging;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HouseAdapter {

    private final VerifyTokenUseCase verifyTokenUseCase;

    private final GetListHouseUseCase getListHouseUseCase;

    private final CreateHouseUseCase createHouseUseCase;

    public GetListHouseResponse getHouses(Paging paging) throws InvalidInputException {
        var houseListResult = getListHouseUseCase.execute(GetListHouseInput.builder()
                        .page(paging.getPage())
                        .block(paging.getBlock())
                        .build());

        return GetListHouseResponseConverter.from(houseListResult);
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

    public boolean verify(String accessToken) throws InvalidInputException {
        var verifyResult = verifyTokenUseCase.execute(VerifyTokenInput.builder()
                .accessToken(accessToken)
                .build());
        if (!verifyResult.isResult()) {
            throw new InvalidInputException(verifyResult.getMessage());
        }

        return false;
    }
}
