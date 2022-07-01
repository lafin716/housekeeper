package com.lafin.housekeeper.presenter.api.room.request;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequest implements RequestModel {

    private Long userId;

    private Long houseId;

    private String name;
}
