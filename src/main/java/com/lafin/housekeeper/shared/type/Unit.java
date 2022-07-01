package com.lafin.housekeeper.shared.type;

public enum Unit {
    EA("개"),
    LITER("L"),
    MILLILITER("ml"),
    CENTIMETER("cm"),
    METER("m"),
    PERCENT("%");

    private String code;

    Unit(String code) {
        this.code = code;
    }

    public static Unit of(String code) {
        try {
            for (Unit type : Unit.values()) {
                if (code.equalsIgnoreCase(type.code)) {
                    return type;
                }
            }
        } catch (Exception e) {
            throw new UnknownTypeException();
        }

        throw new UnknownTypeException();
    }

    public String getCode() {
        return this.code;
    }
}
