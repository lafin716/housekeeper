package com.lafin.housekeeper.shared.type;

public enum UserType {
    ADMIN("admin"),
    USER("user");

    private String code;

    UserType(String code) {
        this.code = code;
    }

    public static UserType of(String code) {
        try {
            return UserType.valueOf(code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
