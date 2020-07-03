package com.nott.security.common.client;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 15:38
 * @Modified By:
 **/
public class OAuth2UserClientFeignConfiguration {
    public OAuth2UserClientFeignConfiguration() {
    }

    @Bean(
            name = {"userFeignClientInterceptor"}
    )
    public RequestInterceptor getUserFeignClientInterceptor() throws IOException {
        return new UserFeignClientInterceptor();
    }
}
