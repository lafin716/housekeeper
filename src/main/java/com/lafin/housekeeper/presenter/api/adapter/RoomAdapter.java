package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.room.usecase.CreateRoomUseCase;
import com.lafin.housekeeper.domain.room.usecase.GetListRoomUseCase;
import com.lafin.housekeeper.domain.room.usecase.input.CreateRoomInput;
import com.lafin.housekeeper.domain.room.usecase.input.GetListRoomInput;
import com.lafin.housekeeper.domain.user.usecase.VerifyTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.VerifyTokenInput;
import com.lafin.housekeeper.presenter.api.request.CreateRoomRequest;
import com.lafin.housekeeper.presenter.api.response.CreateRoomResponse;
import com.lafin.housekeeper.presenter.api.response.GetListRoomResponse;
import com.lafin.housekeeper.presenter.api.response.convert.GetListRoomResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomAdapter {

    private final VerifyTokenUseCase verifyTokenUseCase;

    private final GetListRoomUseCase getListRoomUseCase;

    private final CreateRoomUseCase createRoomUseCase;

    public GetListRoomResponse getRooms(Paging paging) throws InvalidInputException {
        var roomListResult = getListRoomUseCase.execute(GetListRoomInput.builder()
                        .page(paging.getPage())
                        .block(paging.getBlock())
                        .build());

        return GetListRoomResponseConverter.from(roomListResult);
    }

    public CreateRoomResponse addRoom(CreateRoomRequest request) throws InvalidInputException {
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

    public boolean verify(String accessToken) throws InvalidInputException {
        var verifyResult = verifyTokenUseCase.execute(VerifyTokenInput.builder()
                .accessToken(accessToken)
                .build());
        if (!verifyResult.isResult()) {
            throw new InvalidInputException(verifyResult.getMessage());
        }

        return false;
    }
}
