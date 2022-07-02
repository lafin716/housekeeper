package com.lafin.housekeeper.domain.house.usecase;

import com.lafin.housekeeper.domain.house.gateway.HouseGateway;
import com.lafin.housekeeper.domain.house.usecase.input.DeleteHouseInput;
import com.lafin.housekeeper.domain.house.usecase.output.GetHouseOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteHouseUseCase implements UseCase<DeleteHouseInput, DefaultOutput> {

    private final HouseGateway gateway;

    @Override
    public DefaultOutput execute(DeleteHouseInput input) throws InvalidInputException {
        input.validate();

        try {
            var house = gateway.findByUserIdAndHouseId(input.getUserId(), input.getHouseId());
            if (Objects.isNull(house)) {
                return DefaultOutput.fail("집 정보가 없습니다.");
            }

            if (gateway.hasRooms(input.getUserId(), input.getHouseId())) {
                return DefaultOutput.fail("집에 방이 있어 삭제할 수 없습니다.");
            }

            if (!gateway.remove(input.getHouseId())) {
                return DefaultOutput.fail("집이 삭제 되지 않았습니다.");
            }

            return DefaultOutput.ok("집이 삭제 되었습니다.");
        } catch (Exception e) {
            return DefaultOutput.fail(e.getMessage());
        }
    }
}
