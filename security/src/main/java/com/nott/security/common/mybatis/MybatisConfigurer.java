package com.nott.security.common.mybatis;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 16:11
 * @Modified By:
 **/
@Configuration
public class MybatisConfigurer {

    public MybatisConfigurer() {
    }

    @Bean
    ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
