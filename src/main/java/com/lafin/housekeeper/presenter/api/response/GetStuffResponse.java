package com.lafin.housekeeper.presenter.api.response;

import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.status.StuffStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
public class GetStuffResponse implements ResponseModel {

    private boolean result;

    private String message;

    private Stuff stuff;

    @Getter
    @Builder
    @ToString
    public static class Stuff {

        private Long id;

        private Long userId;

        private Long roomId;

        private String name;

        private StuffStatus status;

        private float amount;

        private String unit;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}
