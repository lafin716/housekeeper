package com.lafin.housekeeper.domain.stuff.usecase;

import com.lafin.housekeeper.domain.stuff.gateway.StuffGateway;
import com.lafin.housekeeper.domain.stuff.usecase.input.GetListStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.input.GetStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetListStuffOutput;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetStuffOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetStuffUseCase implements UseCase<GetStuffInput, GetStuffOutput> {

    private final StuffGateway gateway;

    @Override
    public GetStuffOutput execute(GetStuffInput input) throws InvalidInputException {
        input.validate();

        try {
            var stuff = gateway.findByUserIdAndStuffId(input.getUserId(), input.getStuffId());
            return GetStuffOutput.ok(stuff);
        } catch (Exception e) {
            return GetStuffOutput.fail(e.getMessage());
        }
    }
}
