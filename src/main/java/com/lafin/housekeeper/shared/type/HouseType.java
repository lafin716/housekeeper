package com.lafin.housekeeper.shared.type;

public enum HouseType {
    APARTMENT("아파트"),
    VILLA("빌라"),
    MALL("상가"),
    STORAGE("창고"),
    ETC("기타");
    
    private String name;
    
    HouseType(String name) {
        this.name = name;
    }

    public static HouseType of(String name) {
        try {
            for (HouseType type : HouseType.values()) {
                if (name.equalsIgnoreCase(type.name)) {
                    return type;
                }
            }
        } catch (Exception e) {
            return HouseType.ETC;
        }

        return HouseType.ETC;
    }

}
