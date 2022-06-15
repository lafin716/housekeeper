package com.lafin.housekeeper.domain.auth.usecase.input;

import com.lafin.housekeeper.shared.contract.domain.usecase.Input;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;

public class CreateTokenInput implements Input {

    @Override
    public boolean validate() throws InvalidInputException {
        return false;
    }
}
