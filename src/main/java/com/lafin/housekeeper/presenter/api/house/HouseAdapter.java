package com.lafin.housekeeper.presenter.api.house;

import com.lafin.housekeeper.domain.user.interactor.VerifyTokenInteractor;
import com.lafin.housekeeper.presenter.api.house.response.GetListHouseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HouseAdapter {

    private VerifyTokenInteractor verifyTokenInteractor;

    public GetListHouseResponse getHouses() {

        return null;
    }
}
