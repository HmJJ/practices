package com.nott.io.code.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by allen on 2016/4/8.
 */
public class NumberUtils {

    private static final Logger log = LoggerFactory.getLogger(NumberUtils.class);

    public static final BigDecimal Hundred = new BigDecimal("100");

    public static String toString(BigDecimal b1) {

        if(b1 == null) {
            return "";
        }
        b1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP);
        return b1.toString();
    }

    public static String toFengString(BigDecimal b1) {

        if(b1 == null) {
            return "";
        }
        BigDecimal b2 = b1.multiply(Hundred);
        b2 = b2.setScale(0, BigDecimal.ROUND_HALF_UP);
        return Long.toString(b2.longValue());
    }

    public static BigDecimal getFromFeng(String feng) {

        BigDecimal b1 = new BigDecimal(feng);
        BigDecimal b2 = b1.divide(Hundred);

        b2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP);

        return b2;
    }

    public static Integer add(Integer t1, Integer t2) {

        if(t1 == null ) {
            t1 = 0;
        }
        if(t2 == null) {
            t2 = 0;
        }
        return t1 + t2;
    }

    public static void main(String[] args) {

        String a = toFengString(new BigDecimal("0.04"));

        log.info(getFromFeng("6").toString());

    }
}
