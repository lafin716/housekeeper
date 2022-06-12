package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.usecase.input.CreateUserInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;

public interface CreateUserUseCase extends UseCase<CreateUserInput, DefaultOutput> {
}
