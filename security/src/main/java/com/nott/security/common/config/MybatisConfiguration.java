package com.nott.security.common.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/8/11 18:38
 * @Modified By:
 **/
@Configuration
public class MybatisConfiguration {
    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                //表字段转java bean使用驼峰
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
