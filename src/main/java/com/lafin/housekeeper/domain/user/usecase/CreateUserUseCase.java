package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.usecase.input.CreateUserInput;
import com.lafin.housekeeper.domain.user.usecase.output.CreateUserOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;

public interface CreateUserUseCase extends UseCase<CreateUserInput, CreateUserOutput> {
}
