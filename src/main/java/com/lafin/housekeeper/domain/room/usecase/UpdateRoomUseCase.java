package com.lafin.housekeeper.domain.room.usecase;

import com.lafin.housekeeper.domain.room.gateway.RoomGateway;
import com.lafin.housekeeper.domain.room.usecase.input.UpdateRoomInput;
import com.lafin.housekeeper.domain.room.usecase.output.CreateRoomOutput;
import com.lafin.housekeeper.domain.room.usecase.output.GetRoomOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateRoomUseCase implements UseCase<UpdateRoomInput, GetRoomOutput> {

    private final RoomGateway gateway;

    @Override
    public GetRoomOutput execute(UpdateRoomInput input) throws InvalidInputException {
        input.validate();

        try {
            var room = gateway.findByUserIdAndRoomId(input.getUserId(), input.getRoomId());
            if (Objects.isNull(room)) {
                return GetRoomOutput.fail("방 정보가 없습니다.");
            }

            var isExists = gateway.isDuplicatedRoom(input.getUserId(), room.getHouseId(), room.getId(), input.getName());
            if (isExists) {
                return GetRoomOutput.fail("중복된 방 이름입니다.");
            }

            room.updateName(input.getName());

            var saved = gateway.save(room);
            return GetRoomOutput.builder()
                    .result(true)
                    .room(saved)
                    .build();
        } catch (Exception e) {
            return GetRoomOutput.fail(e.getMessage());
        }
    }
}