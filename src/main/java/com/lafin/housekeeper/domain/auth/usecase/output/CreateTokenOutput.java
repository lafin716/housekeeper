package com.lafin.housekeeper.domain.auth.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateTokenOutput implements Output {

    private boolean result;
}
