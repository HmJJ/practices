package com.nott.external.code.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/27 14:43
 * @Modified By:
 **/
public class SHA {

    public static final String KEY_SHA = "SHA-256";
    public static final String SALT = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 字符串sha加密
     * @param s 要加密的字符串
     * @return 加密后的字符串
     */
    public static String shaEncryption(String s) {
        BigInteger sha =null;
        byte[] bys = s.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(bys);
            sha = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sha.toString(32);
    }

    /**
     * 字符串+随机盐 sha加密
     * @param bowmann
     * @param expiration_time 过期时间
     * @return 盐和加密后的字符串
     */
    public static Map<String,String> getResult(String bowmann, String expiration_time){
        Map<String,String> map=new HashMap<String,String>();
        map.put("salt", SALT);//盐
        map.put("token", shaEncryption( expiration_time + SALT));//加密后的密码
        return map;
    }


}
