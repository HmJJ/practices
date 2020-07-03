package com.nott.security.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 15:29
 * @Modified By:
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@FeignClient
public @interface AuthorizedUserFeignClient {
    @AliasFor(
            annotation = FeignClient.class,
            attribute = "name"
    )
    String name() default "";

    @AliasFor(
            annotation = FeignClient.class,
            attribute = "configuration"
    )
    Class<?>[] configuration() default {OAuth2UserClientFeignConfiguration.class};

    String url() default "";

    boolean decode404() default false;

    Class<?> fallback() default void.class;

    String path() default "";
}
