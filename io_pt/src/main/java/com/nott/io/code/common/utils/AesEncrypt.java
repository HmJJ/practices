package com.nott.io.code.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


/**
 * Created by junweiwang on 2016/12/18.
 */
public class AesEncrypt {

    private static Logger log = LoggerFactory.getLogger(AesEncrypt.class);

    public static final String sKey = "b232a59c5s50d37d";
    public static final String sIv = "304a1j2e1ek62b81";
    public static final String transform = "AES/CBC/PKCS5Padding";


    public static String encrypt(String sSrc){

        if(StringUtils.isEmpty(sSrc)) {
            return "";
        }
        String result = "";
        try {
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(transform);//"算法/模式/补码方式"
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes());
            result = Base64.getEncoder().encodeToString(encrypted);
        }catch (Exception e) {
            log.error("AesEncrypt.encrypt error, message{}, src{}", e.getMessage(),sSrc);
        }
        return result;
    }

    public static String decrypt(String sSrc) {

        if(StringUtils.isEmpty(sSrc) || sSrc.length() < 16) {
            return "";
        }
        try{
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(transform);
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception ex) {
            log.error("AesEncrypt.decrypt error, message{}, src{}", ex.getMessage(),sSrc);
            return "";
        }
    }

}
