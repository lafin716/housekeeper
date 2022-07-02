package com.lafin.housekeeper.domain.house.usecase;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.usecase.input.GetHouseInput;
import com.lafin.housekeeper.domain.house.usecase.input.UpdateHouseInput;
import com.lafin.housekeeper.domain.house.usecase.output.CreateHouseOuput;
import com.lafin.housekeeper.domain.house.usecase.output.GetHouseOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateHouseUseCase implements UseCase<UpdateHouseInput, GetHouseOutput> {

    private final HouseGateway houseGateway;

    @Override
    public GetHouseOutput execute(UpdateHouseInput input) throws InvalidInputException {
        input.validate();

        try {
            var house = houseGateway.findByUserIdAndHouseId(input.getUserId(), input.getHouseId());
            if (Objects.isNull(house)) {
                return GetHouseOutput.fail("집 정보가 없습니다.");
            }

            var isExists = houseGateway.isDuplicatedHouse(input.getUserId(), input.getName());
            if (isExists) {
                return GetHouseOutput.fail("중복된 건물 이름입니다.");
            }

            house.updateName(input.getName());

            var savedHouse = houseGateway.save(house);
            return GetHouseOutput.builder()
                    .result(true)
                    .house(savedHouse)
                    .build();
        } catch (Exception e) {
            return GetHouseOutput.fail(e.getMessage());
        }
    }
}