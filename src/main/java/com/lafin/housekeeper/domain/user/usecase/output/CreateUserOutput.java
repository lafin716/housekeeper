package com.lafin.housekeeper.domain.user.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserOutput implements Output {

    private boolean result;

    private String message;
}
