package com.lafin.housekeeper.presenter.api.house.request;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateHouseRequest implements RequestModel {

    private Long userId;

    private String type;

    private String name;
}
