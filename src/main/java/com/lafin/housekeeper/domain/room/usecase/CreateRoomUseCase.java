package com.lafin.housekeeper.domain.room.usecase;

import com.lafin.housekeeper.domain.room.gateway.RoomGateway;
import com.lafin.housekeeper.domain.room.model.Room;
import com.lafin.housekeeper.domain.room.usecase.input.CreateRoomInput;
import com.lafin.housekeeper.domain.room.usecase.output.CreateRoomOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateRoomUseCase implements UseCase<CreateRoomInput, CreateRoomOutput> {

    private final RoomGateway gateway;

    @Override
    public CreateRoomOutput execute(CreateRoomInput input) throws InvalidInputException {
        input.validate();

        try {
            var isExists = gateway.isDuplicatedRoom(input.getUserId(), input.getHouseId(), input.getName());
            if (isExists) {
                return CreateRoomOutput.fail("중복된 방 이름입니다.");
            }

            var room = Room.builder()
                    .userId(input.getUserId())
                    .houseId(input.getHouseId())
                    .name(input.getName())
                    .build();
            room.created();

            var savedRoom = gateway.save(room);
            if (Objects.isNull(savedRoom)) {
                return CreateRoomOutput.fail("방이 등록되지 못했습니다.");
            }

            return CreateRoomOutput.ok("", null);
        } catch (Exception e) {
            return CreateRoomOutput.fail(e.getMessage());
        }
    }
}
