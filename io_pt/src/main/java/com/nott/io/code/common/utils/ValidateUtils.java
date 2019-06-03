package com.nott.io.code.common.utils;



import com.wayonsys.common.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;


import java.util.regex.Pattern;

/**
 * Created by allen on 2016/4/23.
 */
public class ValidateUtils {


    public static void checkMobile(String mobile) {

        String patten = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])[0-9]{8}$";

        if(StringUtils.isBlank(mobile)) {
            throw new ServiceException("请输入手机号码");
        }
        if(!Pattern.matches(patten, mobile)) {
            throw new ServiceException("手机号码格式错误");
        }
    }

    public static void checkIdNumber(String idNumber) {

        if(StringUtils.isBlank(idNumber)) {
            throw new ServiceException("请输入身份证号码");
        }
        if(idNumber.length() != 18) {
            throw new ServiceException("身份证号码长度错误");
        }
        String patten = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

        if(!Pattern.matches(patten,idNumber)) {
            throw new ServiceException("身份证号码格式错误");
        }

    }



}
