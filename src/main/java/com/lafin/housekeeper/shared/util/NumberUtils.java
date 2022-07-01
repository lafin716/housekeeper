package com.lafin.housekeeper.shared.util;

import java.util.Objects;

public class NumberUtils {

    public static boolean isNegative(Long number) {
        if (Objects.isNull(number)) {
            return true;
        }

        if (number > 0L) {
            return false;
        }

        return true;
    }

    public static boolean isNegative(float number) {
        if (number > 0f) {
            return false;
        }

        return true;
    }

    public static boolean isPositive(Long number) {
        if (Objects.isNull(number)) {
            return false;
        }

        if (number <= 0L) {
            return false;
        }

        return true;
    }

    public static boolean isPositive(float number) {
        if (number <= 0f) {
            return false;
        }

        return true;
    }
}
