package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.usecase.input.SignInInput;
import com.lafin.housekeeper.domain.user.usecase.output.SignInOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;

public interface SignInUseCase extends UseCase<SignInInput, SignInOutput> {
}
