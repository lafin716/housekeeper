package com.lafin.housekeeper.domain.auth.model;

import com.lafin.housekeeper.shared.type.ScopeType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
class Scope {

    private Long id;

    private ScopeType type;

    private boolean accepted;

    void accepted() {
        this.accepted = true;
    }

    void denied() {
        this.accepted = false;
    }
}
