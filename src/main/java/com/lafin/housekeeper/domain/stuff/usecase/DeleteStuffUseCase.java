package com.lafin.housekeeper.domain.stuff.usecase;

import com.lafin.housekeeper.domain.stuff.gateway.StuffGateway;
import com.lafin.housekeeper.domain.stuff.usecase.input.DeleteStuffInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteStuffUseCase implements UseCase<DeleteStuffInput, DefaultOutput> {

    private final StuffGateway gateway;

    @Override
    public DefaultOutput execute(DeleteStuffInput input) throws InvalidInputException {
        input.validate();

        try {
            var house = gateway.findByUserIdAndStuffId(input.getUserId(), input.getStuffId());
            if (Objects.isNull(house)) {
                return DefaultOutput.fail("물건 정보가 없습니다.");
            }

            if (!gateway.remove(input.getStuffId())) {
                return DefaultOutput.fail("물건이 삭제 되지 않았습니다.");
            }

            return DefaultOutput.ok("물건이 삭제 되었습니다.");
        } catch (Exception e) {
            return DefaultOutput.fail(e.getMessage());
        }
    }
}
