package com.lafin.housekeeper.domain.house.usecase;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.model.House;
import com.lafin.housekeeper.domain.house.usecase.input.CreateHouseInput;
import com.lafin.housekeeper.domain.house.usecase.output.CreateHouseOuput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateHouseUseCase implements UseCase<CreateHouseInput, CreateHouseOuput> {

    private final HouseGateway houseGateway;

    @Override
    public CreateHouseOuput execute(CreateHouseInput input) throws InvalidInputException {
        input.validate();

        try {
            var isExists = houseGateway.isDuplicatedHouse(input.getUserId(), input.getName());
            if (isExists) {
                return CreateHouseOuput.fail("중복된 건물 이름입니다.");
            }

            var house = House.builder()
                    .userId(input.getUserId())
                    .name(input.getName())
                    .type(input.getType())
                    .build();
            house.created();

            var savedHouse = houseGateway.save(house);
            if (Objects.isNull(savedHouse)) {
                return CreateHouseOuput.fail("건물이 등록되지 못했습니다.");
            }
            
            return CreateHouseOuput.builder()
                    .result(true)
                    .message("건물이 등록되었습니다.")
                    .build();
        } catch (Exception e) {
            return CreateHouseOuput.fail(e.getMessage());
        }
    }
}
