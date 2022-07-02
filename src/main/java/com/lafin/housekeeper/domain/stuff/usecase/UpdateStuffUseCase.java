package com.lafin.housekeeper.domain.stuff.usecase;

import com.lafin.housekeeper.domain.stuff.gateway.StuffGateway;
import com.lafin.housekeeper.domain.stuff.usecase.input.UpdateStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.output.CreateStuffOutput;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetStuffOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateStuffUseCase implements UseCase<UpdateStuffInput, GetStuffOutput> {

    private final StuffGateway gateway;

    @Override
    public GetStuffOutput execute(UpdateStuffInput input) throws InvalidInputException {
        input.validate();

        try {
            var stuff = gateway.findByUserIdAndStuffId(input.getUserId(), input.getStuffId());
            if (Objects.isNull(stuff)) {
                return GetStuffOutput.fail("물건 정보가 없습니다.");
            }

            var isExists = gateway.isDuplicatedStuff(input.getUserId(), stuff.getRoomId(), input.getName());
            if (isExists) {
                return GetStuffOutput.fail("중복된 물건 이름입니다.");
            }

            stuff.updateStuff(input.getName(), input.getUnit());

            var saved = gateway.save(stuff);
            return GetStuffOutput.builder()
                    .result(true)
                    .stuff(saved)
                    .build();
        } catch (Exception e) {
            return GetStuffOutput.fail(e.getMessage());
        }
    }
}