package com.lafin.housekeeper.domain.house.interacter;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.usecase.GetListHouseUseCase;
import com.lafin.housekeeper.domain.house.usecase.input.GetListHouseInput;
import com.lafin.housekeeper.domain.house.usecase.output.GetListHouseOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetListHouseInteracter implements GetListHouseUseCase {

    private final HouseGateway houseGateway;

    @Override
    public GetListHouseOutput execute(GetListHouseInput input) throws InvalidInputException {
        input.validate();



        return null;
    }
}
