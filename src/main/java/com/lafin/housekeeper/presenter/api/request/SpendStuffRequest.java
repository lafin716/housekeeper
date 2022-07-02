package com.lafin.housekeeper.presenter.api.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SpendStuffRequest {

    private Long userId;

    private Long stuffId;

    private float amount;
}
