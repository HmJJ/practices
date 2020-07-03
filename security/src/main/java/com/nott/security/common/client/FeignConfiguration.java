package com.nott.security.common.client;

import feign.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 16:02
 * @Modified By:
 **/
@Configuration
@EnableFeignClients(
        basePackages = {"com.nott"}
)
@Import({FeignClientsConfiguration.class})
public class FeignConfiguration {
    public FeignConfiguration() {
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean({"restfulTemplate"})
    public RestTemplate restTemplate() {
        return this.create();
    }

    @Bean({"feignTemplate"})
    @LoadBalanced
    public RestTemplate feignTemplate() {
        return this.create();
    }

    private RestTemplate create() {
        RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(30000).build();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultRequestConfig(config).setRetryHandler(new DefaultHttpRequestRetryHandler(3, false));
        HttpClient httpClient = builder.build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
