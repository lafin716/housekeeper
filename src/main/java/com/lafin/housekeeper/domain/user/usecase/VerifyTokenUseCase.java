package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.usecase.input.VerifyTokenInput;
import com.lafin.housekeeper.domain.user.usecase.output.VerifyTokenOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;

public interface VerifyTokenUseCase extends UseCase<VerifyTokenInput, VerifyTokenOutput> {
}
