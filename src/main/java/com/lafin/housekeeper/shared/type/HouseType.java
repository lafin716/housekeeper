package com.lafin.housekeeper.shared.type;

public enum HouseType {
    APARTMENT("apartment", "아파트"),
    VILLA("villa", "빌라"),
    MALL("mall", "상가"),
    STORAGE("storage", "창고"),
    ETC("etc", "기타");

    private String code;

    private String name;
    
    HouseType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static HouseType of(String code) {
        try {
            for (HouseType type : HouseType.values()) {
                if (code.equalsIgnoreCase(type.code)) {
                    return type;
                }
            }
        } catch (Exception e) {
            return HouseType.ETC;
        }

        return HouseType.ETC;
    }

}
