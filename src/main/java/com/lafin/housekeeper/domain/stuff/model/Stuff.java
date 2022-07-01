package com.lafin.housekeeper.domain.stuff.model;

import com.lafin.housekeeper.shared.contract.domain.model.Model;
import com.lafin.housekeeper.shared.status.StuffStatus;
import com.lafin.housekeeper.shared.type.Unit;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Stuff implements Model {

    private Long id;

    private Long userId;

    private Long roomId;

    private StuffStatus status;

    private String name;

    private float amount;

    private Unit unit;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void created() {
        this.status = StuffStatus.EMPTY;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void add(float amount) {
        this.amount += amount;
        if (amount > 0f) {
            this.status = StuffStatus.USING;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void use(float amount) {
        this.amount -= amount;
        if (amount < 0f) {
            this.amount = 0;
            this.status = StuffStatus.EMPTY;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void deleted() {
        this.updatedAt = LocalDateTime.now();
        this.status = StuffStatus.NOT_USING;
    }
}
