package com.lafin.housekeeper.shared.contract.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Paging {

    private int page;

    private int block;

    public static Paging of(int page) {
        return of(page, 10);
    }

    public static Paging of(int page, int block) {
        return Paging.builder()
                .page(page)
                .block(block)
                .build();
    }
}
