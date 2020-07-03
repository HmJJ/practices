package com.nott.security.common.config;

import org.springframework.boot.SpringApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 17:28
 * @Modified By:
 **/
public final class DefaultProfileUtil {
    private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";

    private DefaultProfileUtil() {
    }

    public static void addDefaultProfile(SpringApplication app) {
        Map<String, Object> defProperties = new HashMap();
        app.setDefaultProperties(defProperties);
    }
}
