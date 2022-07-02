package com.lafin.housekeeper.domain.stuff.usecase;

import com.lafin.housekeeper.domain.stuff.gateway.StuffGateway;
import com.lafin.housekeeper.domain.stuff.usecase.input.GetListStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.output.GetListStuffOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetListStuffUseCase implements UseCase<GetListStuffInput, GetListStuffOutput> {

    private final StuffGateway gateway;

    @Override
    public GetListStuffOutput execute(GetListStuffInput input) throws InvalidInputException {
        input.validate();

        try {
            var stuffs = gateway.findAll();
            return GetListStuffOutput.ok(stuffs);
        } catch (Exception e) {
            return GetListStuffOutput.fail(e.getMessage());
        }
    }
}
