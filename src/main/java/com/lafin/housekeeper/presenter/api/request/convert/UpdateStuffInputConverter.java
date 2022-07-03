package com.lafin.housekeeper.presenter.api.request.convert;

import com.lafin.housekeeper.domain.stuff.usecase.input.UpdateStuffInput;
import com.lafin.housekeeper.presenter.api.request.UpdateStuffRequest;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.convert.RequestConverter;
import com.lafin.housekeeper.shared.type.Unit;
import org.apache.logging.log4j.util.Strings;

public class UpdateStuffInputConverter implements RequestConverter<UpdateStuffRequest, UpdateStuffInput> {

    @Override
    public UpdateStuffInput toInput(UpdateStuffRequest request) {
        var builder = UpdateStuffInput.builder()
                .userId(request.getUserId())
                .stuffId(request.getStuffId());

        if (Strings.isNotBlank(request.getName())) {
            builder.name(request.getName());
        }

        if (Strings.isNotBlank(request.getUnit())) {
            builder.unit(Unit.of(request.getUnit()));
        }

        return builder.build();
    }

    public static UpdateStuffInput to(UpdateStuffRequest request) {
        var converter = new UpdateStuffInputConverter();
        return converter.toInput(request);
    }
}
