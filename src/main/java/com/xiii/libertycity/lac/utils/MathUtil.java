package com.xiii.libertycity.lac.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public static final double MINIMUM_DIVISOR = ((Math.pow(0.2f, 3) * 8) * 0.15) - 1e-3; // 1e-3 for float imprecision

    public static double gcd(double a, double b) {
        if (a == 0) return 0;
        if (a < b) {
            double temp = a;
            a = b;
            b = temp;
        }
        while (a > MINIMUM_DIVISOR) {
            double temp = a - (Math.floor(a / b) * b);
            a = b;
            b = temp;
        }
        return a;
    }
}
