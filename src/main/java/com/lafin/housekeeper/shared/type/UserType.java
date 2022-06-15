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
            for (UserType type : UserType.values()) {
                if (code.equalsIgnoreCase(type.code)) {
                    return type;
                }
            }
        } catch (Exception e) {
            return UserType.UNKNOWN;
        }

        return UserType.UNKNOWN;
    }

    public boolean isUnknown() {
        return this.code.equals("");
    }
}
