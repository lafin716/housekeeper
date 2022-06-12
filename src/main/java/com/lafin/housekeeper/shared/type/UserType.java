package com.lafin.housekeeper.shared.type;

public enum UserType {
    ADMIN("admin"),
    USER("user"),
    UNKNOWN("");

    private String code;

    UserType(String code) {
        this.code = code;
    }

    public static UserType of(String code) {
        try {
            return UserType.valueOf(code);
        } catch (Exception e) {
            return UserType.UNKNOWN;
        }
    }

    public boolean isUnknown() {
        return this.code.equals("");
    }
}
