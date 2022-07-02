package com.lafin.housekeeper.presenter.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class GetHouseResponse implements ResponseModel {

    private boolean result;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private House house;

    @Getter
    @Builder
    @ToString
    public static class House {

        private Long id;

        private Long userId;

        private String name;

        private HouseType type;

        private LocalDateTime createdAt;
    }

    public static GetHouseResponse ok(House house) {
        return GetHouseResponse.builder()
                .result(true)
                .house(house)
                .build();
    }

    public static GetHouseResponse fail(String message) {
        return GetHouseResponse.builder()
                .result(true)
                .message(message)
                .build();
    }
}
