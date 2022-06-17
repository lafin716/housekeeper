package com.lafin.housekeeper.domain.house.usecase.output;

import com.lafin.housekeeper.shared.contract.domain.usecase.Output;
import com.lafin.housekeeper.shared.type.HouseType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class GetListHouseOutput implements Output {

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

        private LocalDateTime updatedAt;
    }

    public static GetListHouseOutput fail(String message) {
        return GetListHouseOutput.builder()
                .result(false)
                .message(message)
                .build();
    }
}
