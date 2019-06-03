package com.nott.io.code.common.utils;

import com.wayonsys.common.enums.Gender;

import java.time.LocalDate;

/**
 * Created by allen on 2017/1/6.
 */
public class IdNumberUtils {

    private static final String PATTERN = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

    public static Gender getGender(String idNumber) {

        int sex = Integer.parseInt(idNumber.substring(16,17));
        if(sex % 2 == 0) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    public static int getAge(String idNumber) {
        LocalDate birth = getBirthDate(idNumber);
        int age = DateUtils.getAge(birth);
        return age;
    }

    public static LocalDate getBirthDate(String idNumber) {
        String birth = idNumber.substring(6);
        int year = Integer.parseInt(birth.substring(0,4));
        int month = Integer.parseInt(birth.substring(4,6));
        int day = Integer.parseInt(birth.substring(6,8));
        return LocalDate.of(year,month,day);
    }
}
