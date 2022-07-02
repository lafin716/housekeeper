package com.lafin.housekeeper.domain.house.usecase;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.usecase.input.GetListHouseInput;
import com.lafin.housekeeper.domain.house.usecase.output.GetListHouseOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetListHouseUseCase implements UseCase<GetListHouseInput, GetListHouseOutput> {

    private final HouseGateway houseGateway;

    @Override
    public GetListHouseOutput execute(GetListHouseInput input) throws InvalidInputException {
        input.validate();

        try {
            var houses = houseGateway.findAllByUserId(input.getUserId(), input.getPaging());
            return GetListHouseOutput.builder()
                    .result(true)
                    .houses(houses)
                    .build();
        } catch (Exception e) {
            return GetListHouseOutput.builder()
                    .result(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}