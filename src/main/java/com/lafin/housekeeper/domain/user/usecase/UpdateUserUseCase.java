package com.lafin.housekeeper.domain.user.usecase;

import com.lafin.housekeeper.domain.user.usecase.input.UpdateUserInput;
import com.lafin.housekeeper.shared.contract.domain.usecase.DefaultOutput;
import com.lafin.housekeeper.shared.contract.domain.usecase.UseCase;

public interface UpdateUserUseCase extends UseCase<UpdateUserInput, DefaultOutput> {
}
