package com.nott.security.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/8/12 10:42
 * @Modified By:
 **/
@Component
public class SpringContextsUtils implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextsUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static Object getBean(Class<?> type) throws BeansException {
        return applicationContext.getBean(type);
    }

    public static Object getBean(String name, Class<?> type) throws BeansException {
        return applicationContext.getBean(name, type);
    }

}
