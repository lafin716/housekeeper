package com.lafin.housekeeper.domain.room.usecase;

import com.lafin.housekeeper.domain.room.gateway.RoomGateway;
import com.lafin.housekeeper.domain.room.usecase.input.DeleteRoomInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteRoomUseCase implements UseCase<DeleteRoomInput, DefaultOutput> {

    private final RoomGateway gateway;

    @Override
    public DefaultOutput execute(DeleteRoomInput input) throws InvalidInputException {
        input.validate();

        try {
            var house = gateway.findByUserIdAndRoomId(input.getUserId(), input.getRoomId());
            if (Objects.isNull(house)) {
                return DefaultOutput.fail("집 정보가 없습니다.");
            }

            if (gateway.hasStuff(input.getUserId(), input.getRoomId())) {
                return DefaultOutput.fail("방에 물건이 있어 삭제할 수 없습니다.");
            }

            if (!gateway.remove(input.getRoomId())) {
                return DefaultOutput.fail("방이 삭제 되지 않았습니다.");
            }

            return DefaultOutput.ok("방이 삭제 되었습니다.");
        } catch (Exception e) {
            return DefaultOutput.fail(e.getMessage());
        }
    }
}
