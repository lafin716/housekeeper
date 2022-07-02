package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.room.usecase.GetRoomUseCase;
import com.lafin.housekeeper.domain.room.usecase.input.GetRoomInput;
import com.lafin.housekeeper.domain.stuff.usecase.*;
import com.lafin.housekeeper.domain.stuff.usecase.input.*;
import com.lafin.housekeeper.presenter.api.request.*;
import com.lafin.housekeeper.presenter.api.response.CreateRoomResponse;
import com.lafin.housekeeper.presenter.api.response.GetListStuffResponse;
import com.lafin.housekeeper.presenter.api.response.GetStuffResponse;
import com.lafin.housekeeper.presenter.api.response.convert.GetListStuffResponseConverter;
import com.lafin.housekeeper.presenter.api.response.convert.GetStuffResponseConverter;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.DefaultResponse;
import com.lafin.housekeeper.shared.contract.common.Paging;
import com.lafin.housekeeper.shared.type.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StuffAdapter {
    private final GetRoomUseCase getRoomUseCase;
    private final CreateStuffUseCase createStuffUseCase;
    private final GetListStuffUseCase getListStuffUseCase;
    private final GetStuffUseCase getStuffUseCase;
    private final SpendStuffUseCase spendStuffUseCase;
    private final FillStuffUseCase fillStuffUseCase;
    private final UpdateStuffUseCase updateStuffUseCase;
    private final DeleteStuffUseCase deleteStuffUseCase;

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

    public GetListStuffResponse getStuffs(Long userId, Paging paging) throws InvalidInputException {
        var stuffs = getListStuffUseCase.execute(GetListStuffInput.builder()
                        .userId(userId)
                        .paging(paging)
                        .build());
        if (!stuffs.isResult()) {
            return GetListStuffResponse.builder()
                    .result(false)
                    .message(stuffs.getMessage())
                    .build();
        }

        return GetListStuffResponseConverter.from(stuffs);
    }

    public GetStuffResponse getStuff(Long userId, Long stuffId) throws InvalidInputException {
        var stuff = getStuffUseCase.execute(GetStuffInput.builder()
                        .userId(userId)
                        .stuffId(stuffId)
                        .build());
        return GetStuffResponseConverter.from(stuff);
    }

    public GetStuffResponse spendStuff(SpendStuffRequest request) throws InvalidInputException {
        var stuff = spendStuffUseCase.execute(SpendStuffInput.builder()
                        .userId(request.getUserId())
                        .stuffId(request.getStuffId())
                        .useAmount(request.getAmount())
                .build());

        return GetStuffResponseConverter.from(stuff);
    }

    public GetStuffResponse fillStuff(FillStuffRequest request) throws InvalidInputException {
        var stuff = fillStuffUseCase.execute(FillStuffInput.builder()
                .userId(request.getUserId())
                .stuffId(request.getStuffId())
                .fillAmount(request.getAmount())
                .build());

        return GetStuffResponseConverter.from(stuff);
    }

    public GetStuffResponse updateStuff(UpdateStuffRequest request) throws InvalidInputException {
        var stuff = updateStuffUseCase.execute(UpdateStuffInput.builder()
                        .userId(request.getUserId())
                        .stuffId(request.getStuffId())
                        .name(request.getName())
                        .unit(Unit.of(request.getUnit()))
                        .build());

        return GetStuffResponseConverter.from(stuff);
    }

    public DefaultResponse deleteStuff(Long userId, Long stuffId) throws InvalidInputException {
        var stuff = deleteStuffUseCase.execute(DeleteStuffInput.builder()
                .userId(userId)
                .stuffId(stuffId)
                .build());
        return DefaultResponse.response(stuff);
    }
}
