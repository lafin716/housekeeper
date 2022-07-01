package com.lafin.housekeeper.presenter.api.adapter;

import com.lafin.housekeeper.domain.user.usecase.VerifyTokenUseCase;
import com.lafin.housekeeper.domain.user.usecase.input.VerifyTokenInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;

public abstract class BaseAdapter {

    private final VerifyTokenUseCase verifyTokenUseCase;

    protected BaseAdapter(VerifyTokenUseCase verifyTokenUseCase) {
        this.verifyTokenUseCase = verifyTokenUseCase;
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
