package com.lafin.housekeeper.shared.type;

public enum Unit {
    EA("ea", "개"),
    LITER("l", "L"),
    MILLILITER("ml", "ml"),
    CENTIMETER("cm", "cm"),
    METER("m", "m"),
    PERCENT("percent", "%");

    private String code;
    private String label;

    Unit(String code, String label) {
        this.code = code;
        this.label = label;
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

    public String getLabel() {
        return this.label;
    }
}
