package com.lafin.housekeeper.shared.status;

public enum StuffStatus {
    USING("사용중"),
    EMPTY("비어있음"),
    NOT_USING("사용안함");

    private String label;
    
    StuffStatus(String label) {
        this.label = label;
    }
}
