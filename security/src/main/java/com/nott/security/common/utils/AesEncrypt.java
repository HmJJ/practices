package com.nott.security.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:40
 * @Modified By:
 **/
public class AesEncrypt {
    private static Logger log = LoggerFactory.getLogger(AesEncrypt.class);
    public static final String sKey = "b232a59c5s50d38d";
    public static final String sIv = "304a1j2e1ek62b82";
    public static final String transform = "AES/CBC/PKCS5Padding";

    public AesEncrypt() {
    }

    public static String encrypt(String sSrc) {
        if (StringUtils.isBlank(sSrc)) {
            return "";
        } else {
            String result = "";

            try {
                byte[] raw = "b232a59c5s50d38d".getBytes();
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec("304a1j2e1ek62b82".getBytes());
                cipher.init(1, skeySpec, iv);
                byte[] encrypted = cipher.doFinal(sSrc.getBytes());
                result = Base64.getEncoder().encodeToString(encrypted);
            } catch (Exception var7) {
                log.error("AesEncrypt.encrypt error, message{}, src{}", var7.getMessage(), sSrc);
            }

            return result;
        }
    }

    public static String decrypt(String sSrc) {
        if (!StringUtils.isBlank(sSrc) && sSrc.length() >= 16) {
            try {
                byte[] raw = "b232a59c5s50d38d".getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec("304a1j2e1ek62b82".getBytes());
                cipher.init(2, skeySpec, iv);
                byte[] encrypted1 = Base64.getDecoder().decode(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception var8) {
                log.error("AesEncrypt.decrypt error, message{}, src{}", var8.getMessage(), sSrc);
                return "";
            }
        } else {
            return "";
        }
    }
}
