package com.lafin.housekeeper.presenter.api.request;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomRequest implements RequestModel {

    private Long userId;

    private Long roomId;

    private String name;
}
