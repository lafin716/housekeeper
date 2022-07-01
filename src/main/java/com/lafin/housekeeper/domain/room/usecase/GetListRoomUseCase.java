package com.lafin.housekeeper.domain.room.usecase;

import com.lafin.housekeeper.domain.room.gateway.RoomGateway;
import com.lafin.housekeeper.domain.room.usecase.input.GetListRoomInput;
import com.lafin.housekeeper.domain.room.usecase.output.GetListRoomOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetListRoomUseCase implements UseCase<GetListRoomInput, GetListRoomOutput> {

    private final RoomGateway gateway;

    @Override
    public GetListRoomOutput execute(GetListRoomInput input) throws InvalidInputException {
        input.validate();

        try {
            var rooms = gateway.findAll();
            return GetListRoomOutput.builder()
                    .result(true)
                    .rooms(rooms)
                    .build();
        } catch (Exception e) {
            return GetListRoomOutput.fail(e.getMessage());
        }
    }
}
