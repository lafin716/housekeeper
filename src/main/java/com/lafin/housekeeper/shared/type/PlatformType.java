package com.lafin.housekeeper.shared.type;

public enum PlatformType {
    EMAIL("email"),
    FACEBOOK("facebook"),
    GOOGLE("google");

    private String code;

    PlatformType(String code) {
        this.code = code;
    }

    public static PlatformType of(String code) {
        try {
            return PlatformType.valueOf(code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
