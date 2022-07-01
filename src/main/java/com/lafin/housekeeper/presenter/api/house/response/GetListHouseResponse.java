package com.lafin.housekeeper.presenter.api.house.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
public class GetListHouseResponse implements ResponseModel {

    private boolean result;

    private String message;

    private List<House> houses;

    @Getter
    @Builder
    @ToString
    public static class House {

        private Long id;

        private String name;

        private HouseType type;

        private LocalDateTime createdAt;
    }
}
