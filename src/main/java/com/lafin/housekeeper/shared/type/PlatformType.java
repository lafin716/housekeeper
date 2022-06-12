package com.lafin.housekeeper.shared.type;

public enum PlatformType {
    EMAIL("email"),
    FACEBOOK("facebook"),
    GOOGLE("google"),
    UNKNOWN("");

    private String code;

    PlatformType(String code) {
        this.code = code;
    }

    public static PlatformType of(String code) {
        try {
            return PlatformType.valueOf(code);
        } catch (Exception e) {
            return PlatformType.UNKNOWN;
        }
    }

    public boolean isUnknown() {
        return this.code.equals("");
    }
}
