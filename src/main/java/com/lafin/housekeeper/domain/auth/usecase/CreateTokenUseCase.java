package com.lafin.housekeeper.domain.auth.usecase;

import com.lafin.housekeeper.domain.auth.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.auth.usecase.output.CreateTokenOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;

public interface CreateTokenUseCase extends UseCase<CreateTokenInput, CreateTokenOutput> {
}
