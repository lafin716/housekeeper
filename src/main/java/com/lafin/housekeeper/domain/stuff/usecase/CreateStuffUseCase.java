package com.lafin.housekeeper.domain.stuff.usecase;

import com.lafin.housekeeper.domain.stuff.gateway.StuffGateway;
import com.lafin.housekeeper.domain.stuff.model.Stuff;
import com.lafin.housekeeper.domain.stuff.usecase.input.CreateStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.output.CreateStuffOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateStuffUseCase implements UseCase<CreateStuffInput, CreateStuffOutput> {

    private final StuffGateway gateway;

    @Override
    public CreateStuffOutput execute(CreateStuffInput input) throws InvalidInputException {
        input.validate();

        try {
            var isExists = gateway.isDuplicatedStuff(input.getUserId(), input.getRoomId(), input.getName());
            if (isExists) {
                return CreateStuffOutput.fail("중복된 물건 이름입니다.");
            }

            var stuff = Stuff.builder()
                    .userId(input.getUserId())
                    .roomId(input.getRoomId())
                    .name(input.getName())
                    .unit(input.getUnit())
                    .build();
            stuff.created();
            stuff.add(input.getAmount());

            var savedRoom = gateway.save(stuff);
            if (Objects.isNull(savedRoom)) {
                return CreateStuffOutput.fail("물건이 등록되지 못했습니다.");
            }

            return CreateStuffOutput.ok("물건이 등록되었습니다.");
        } catch (Exception e) {
            return CreateStuffOutput.fail(e.getMessage());
        }
    }
}
