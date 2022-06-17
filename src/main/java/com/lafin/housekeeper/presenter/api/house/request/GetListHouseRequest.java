package com.lafin.housekeeper.presenter.api.house.request;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.RequestModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListHouseRequest implements RequestModel {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int block = 10;

    private String accessToken;
}
