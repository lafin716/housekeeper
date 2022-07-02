package com.lafin.housekeeper.domain.house.usecase;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.usecase.input.GetHouseInput;
import com.lafin.housekeeper.domain.house.usecase.input.GetListHouseInput;
import com.lafin.housekeeper.domain.house.usecase.output.GetHouseOutput;
import com.lafin.housekeeper.domain.house.usecase.output.GetListHouseOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetHouseUseCase implements UseCase<GetHouseInput, GetHouseOutput> {

    private final HouseGateway houseGateway;

    @Override
    public GetHouseOutput execute(GetHouseInput input) throws InvalidInputException {
        input.validate();

        try {
            var house = houseGateway.findByUserIdAndHouseId(input.getUserId(), input.getHouseId());
            if (Objects.isNull(house)) {
                return GetHouseOutput.fail("집 정보가 없습니다.");
            }

            return GetHouseOutput.builder()
                    .result(true)
                    .house(house)
                    .build();
        } catch (Exception e) {
            return GetHouseOutput.fail(e.getMessage());
        }
    }
}