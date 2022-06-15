package com.lafin.housekeeper.shared.type;

public enum ScopeType {
    ALL("전체");

    private String name;

    ScopeType(String name) {
        this.name = name;
    }

    public static ScopeType of(String name) {
        try {
            for (ScopeType type : ScopeType.values()) {
                if (name.equalsIgnoreCase(type.name)) {
                    return type;
                }
            }
        } catch (Exception e) {
            throw new UnknownTypeException();
        }

        throw new UnknownTypeException();
    }
}
