package com.lafin.housekeeper.domain.stuff.usecase;

import com.lafin.housekeeper.domain.stuff.gateway.StuffGateway;
import com.lafin.housekeeper.domain.stuff.usecase.input.FillStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.input.SpendStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetStuffOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import com.lafin.housekeeper.shared.status.StuffStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FillStuffUseCase implements UseCase<FillStuffInput, GetStuffOutput> {

    private final StuffGateway gateway;

    @Override
    public GetStuffOutput execute(FillStuffInput input) throws InvalidInputException {
        input.validate();

        var stuff = gateway.findByUserIdAndStuffId(input.getUserId(), input.getStuffId());
        if (Objects.isNull(stuff)) {
            return GetStuffOutput.fail("물건이 없습니다.");
        }

        if (stuff.getStatus() == StuffStatus.NOT_USING) {
            return GetStuffOutput.fail("사용 가능한 물건이 아닙니다.");
        }
        
        stuff.add(input.getFillAmount());
        var savedStuff = gateway.save(stuff);
        if (Objects.isNull(savedStuff)) {
            return GetStuffOutput.fail("물건 충전이 실패했습니다.");
        }

        return GetStuffOutput.ok(savedStuff);
    }
}