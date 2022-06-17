package com.lafin.housekeeper.presenter.api.house.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
public class GetListHouseResponse implements ResponseModel {

    private boolean result;

    private List<House> houses;

    @Getter
    @Builder
    @ToString
    public static class House {

    }
}
