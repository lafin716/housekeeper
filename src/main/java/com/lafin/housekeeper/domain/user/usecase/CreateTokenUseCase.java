package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.usecase.input.CreateTokenInput;
import com.lafin.housekeeper.domain.user.usecase.output.CreateTokenOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;

public interface CreateTokenUseCase extends UseCase<CreateTokenInput, CreateTokenOutput> {
}
