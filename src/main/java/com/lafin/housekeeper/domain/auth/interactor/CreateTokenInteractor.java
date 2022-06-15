package com.lafin.housekeeper.domain.auth.interactor;

import com.lafin.housekeeper.domain.auth.gateway.AuthRepository;
import com.lafin.housekeeper.domain.auth.usecase.CreateTokenUseCase;
import com.lafin.housekeeper.domain.auth.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.auth.usecase.output.CreateTokenOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTokenInteractor implements CreateTokenUseCase {

    private AuthRepository repository;

    @Override
    public CreateTokenOutput execute(CreateTokenInput input) throws InvalidInputException {
        input.validate();



        return null;
    }
}

