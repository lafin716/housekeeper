package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.house.usecase.GetHouseUseCase;
import com.lafin.housekeeper.domain.house.usecase.input.GetHouseInput;
import com.lafin.housekeeper.domain.room.usecase.*;
import com.lafin.housekeeper.domain.room.usecase.input.*;
import com.lafin.housekeeper.presenter.api.request.CreateRoomRequest;
import com.lafin.housekeeper.presenter.api.request.UpdateRoomRequest;
import com.lafin.housekeeper.presenter.api.response.CreateRoomResponse;
import com.lafin.housekeeper.presenter.api.response.GetListRoomResponse;
import com.lafin.housekeeper.presenter.api.response.GetRoomResponse;
import com.lafin.housekeeper.presenter.api.response.convert.GetListRoomResponseConverter;
import com.lafin.housekeeper.presenter.api.response.convert.GetRoomResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.DefaultResponse;
import com.lafin.housekeeper.shared.contract.common.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomAdapter {
    private final GetHouseUseCase getHouseUseCase;
    private final GetListRoomUseCase getListRoomUseCase;
    private final CreateRoomUseCase createRoomUseCase;
    private final GetRoomUseCase getRoomUseCase;
    private final UpdateRoomUseCase updateRoomUseCase;
    private final DeleteRoomUseCase deleteRoomUseCase;

    public GetListRoomResponse getRooms(Long userId, Paging paging) throws InvalidInputException {
        var roomListResult = getListRoomUseCase.execute(GetListRoomInput.builder()
                        .userId(userId)
                        .paging(paging)
                        .build());

        return GetListRoomResponseConverter.from(roomListResult);
    }

    public GetRoomResponse getRoom(Long userId, Long roomId) throws InvalidInputException {
        var room = getRoomUseCase.execute(GetRoomInput.builder()
                        .userId(userId)
                        .roomId(roomId)
                        .build());
        return GetRoomResponseConverter.from(room);
    }

    public CreateRoomResponse addRoom(CreateRoomRequest request) throws InvalidInputException {
        var house = getHouseUseCase.execute(GetHouseInput.builder()
                        .userId(request.getUserId())
                        .houseId(request.getHouseId())
                        .build());
        if (!house.isResult()) {
            return CreateRoomResponse.fail(house.getMessage());
        }

        var addResult = createRoomUseCase.execute(CreateRoomInput.builder()
                        .userId(request.getUserId())
                        .houseId(request.getHouseId())
                        .name(request.getName())
                        .build());
        if (!addResult.isResult()) {
            return CreateRoomResponse.fail(addResult.getMessage());
        }

        return CreateRoomResponse.ok(addResult.getMessage());
    }

    public GetRoomResponse updateRoom(UpdateRoomRequest request) throws InvalidInputException {
        var room = updateRoomUseCase.execute(UpdateRoomInput.builder()
                        .userId(request.getUserId())
                        .roomId(request.getRoomId())
                        .name(request.getName())
                        .build());
        return GetRoomResponseConverter.from(room);
    }

    public DefaultResponse deleteRoom(Long userId, Long roomId) throws InvalidInputException {
        var result = deleteRoomUseCase.execute(DeleteRoomInput.builder()
                        .userId(userId)
                        .roomId(roomId)
                        .build());

        return DefaultResponse.response(result);
    }
}
