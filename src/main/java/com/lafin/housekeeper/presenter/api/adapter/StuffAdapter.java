package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.room.usecase.GetRoomUseCase;
import com.lafin.housekeeper.domain.room.usecase.input.GetRoomInput;
import com.lafin.housekeeper.domain.stuff.usecase.CreateStuffUseCase;
import com.lafin.housekeeper.domain.stuff.usecase.GetListStuffUseCase;
import com.lafin.housekeeper.domain.stuff.usecase.SpendStuffUseCase;
import com.lafin.housekeeper.domain.stuff.usecase.input.CreateStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.input.GetListStuffInput;
import com.lafin.housekeeper.domain.stuff.usecase.input.SpendStuffInput;
import com.lafin.housekeeper.domain.user.usecase.VerifyTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.VerifyTokenInput;
import com.lafin.housekeeper.presenter.api.request.CreateRoomRequest;
import com.lafin.housekeeper.presenter.api.request.CreateStuffRequest;
import com.lafin.housekeeper.presenter.api.request.SpendStuffRequest;
import com.lafin.housekeeper.presenter.api.response.CreateRoomResponse;
import com.lafin.housekeeper.presenter.api.response.GetListStuffResponse;
import com.lafin.housekeeper.presenter.api.response.GetStuffResponse;
import com.lafin.housekeeper.presenter.api.response.convert.GetListStuffResponseConverter;
import com.lafin.housekeeper.presenter.api.response.convert.GetStuffResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.Paging;
import com.lafin.housekeeper.shared.type.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StuffAdapter {
    private final GetRoomUseCase getRoomUseCase;
    private final CreateStuffUseCase createStuffUseCase;
    private final GetListStuffUseCase getListStuffUseCase;
    private final SpendStuffUseCase spendStuffUseCase;

    public CreateRoomResponse addStuff(CreateStuffRequest request) throws InvalidInputException {
        var room = getRoomUseCase.execute(GetRoomInput.builder()
                        .userId(request.getUserId())
                        .roomId(request.getRoomId())
                        .build());
        if (!room.isResult()) {
            return CreateRoomResponse.fail(room.getMessage());
        }

        var addResult = createStuffUseCase.execute(CreateStuffInput.builder()
                        .userId(request.getUserId())
                        .roomId(request.getRoomId())
                        .name(request.getName())
                        .amount(request.getAmount())
                        .unit(Unit.of(request.getUnit()))
                        .build());
        if (!addResult.isResult()) {
            return CreateRoomResponse.fail(addResult.getMessage());
        }

        return CreateRoomResponse.ok(addResult.getMessage());
    }

    public GetListStuffResponse getStuffs(Paging paging) throws InvalidInputException {
        var stuffs = getListStuffUseCase.execute(GetListStuffInput.builder()
                        .page(paging.getPage())
                        .block(paging.getBlock())
                .build());
        if (!stuffs.isResult()) {
            return GetListStuffResponse.builder()
                    .result(false)
                    .message(stuffs.getMessage())
                    .build();
        }

        return GetListStuffResponseConverter.from(stuffs);
    }

    public GetStuffResponse spendStuff(SpendStuffRequest request) throws InvalidInputException {
        var stuff = spendStuffUseCase.execute(SpendStuffInput.builder()
                        .userId(request.getUserId())
                        .stuffId(request.getStuffId())
                        .useAmount(request.getAmount())
                .build());

        return GetStuffResponseConverter.from(stuff);
    }
}
