package com.lafin.housekeeper.domain.room.usecase;

import com.lafin.housekeeper.domain.room.gateway.RoomGateway;
import com.lafin.housekeeper.domain.room.usecase.input.GetListRoomInput;
import com.lafin.housekeeper.domain.room.usecase.input.GetRoomInput;
import com.lafin.housekeeper.domain.room.usecase.output.GetListRoomOutput;
import com.lafin.housekeeper.domain.room.usecase.output.GetRoomOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GetRoomUseCase implements UseCase<GetRoomInput, GetRoomOutput> {

    private final RoomGateway gateway;

    @Override
    public GetRoomOutput execute(GetRoomInput input) throws InvalidInputException {
        input.validate();

        try {
            var room = gateway.findByUserIdAndRoomId(input.getUserId(), input.getRoomId());
            if (Objects.isNull(room)) {
                return GetRoomOutput.fail("방 정보가 없습니다.");
            }

            return GetRoomOutput.builder()
                    .result(true)
                    .room(room)
                    .build();
        } catch (Exception e) {
            return GetRoomOutput.fail(e.getMessage());
        }
    }
}
