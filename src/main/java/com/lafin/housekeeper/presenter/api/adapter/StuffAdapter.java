package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.stuff.usecase.CreateStuffUseCase;
import com.lafin.housekeeper.domain.stuff.usecase.input.CreateStuffInput;
import com.lafin.housekeeper.domain.user.usecase.VerifyTokenUseCase;
import com.lafin.housekeeper.presenter.api.request.CreateRoomRequest;
import com.lafin.housekeeper.presenter.api.request.CreateStuffRequest;
import com.lafin.housekeeper.presenter.api.response.CreateRoomResponse;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.type.Unit;
import org.springframework.stereotype.Component;

@Component
public class StuffAdapter extends BaseAdapter {

    private CreateStuffUseCase createStuffUseCase;

    public StuffAdapter(VerifyTokenUseCase verifyTokenUseCase, CreateStuffUseCase createStuffUseCase) {
        super(verifyTokenUseCase);
        this.createStuffUseCase = createStuffUseCase;
    }

    public CreateRoomResponse addStuff(CreateStuffRequest request) throws InvalidInputException {
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


}
