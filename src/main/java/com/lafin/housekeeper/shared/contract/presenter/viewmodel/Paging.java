package com.lafin.housekeeper.shared.contract.presenter.viewmodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Paging {

    private int page;

    private int block;

    public void setPage(int page) {
        this.page = page > 0 ? page : 1;
    }

    public void setBlock(int block) {
        this.block = block > 0 ? block : 10;
    }
}
