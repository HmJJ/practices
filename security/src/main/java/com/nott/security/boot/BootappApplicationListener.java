package com.nott.security.boot;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 17:01
 * @Modified By:
 **/
public class BootappApplicationListener implements EnvironmentPostProcessor, ApplicationListener<ApplicationEvent>, Ordered {

    private static final Logger log = LoggerFactory.getLogger(BootappApplicationListener.class);
    public static final int DEFAULT_ORDER = -2147483643;

    public BootappApplicationListener() {
    }

    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            log.info("ApplicationEnvironmentPreparedEvent");
            this.onApplicationEnvironmentPreparedEvent((ApplicationEnvironmentPreparedEvent)event);
        }

        if (event instanceof ApplicationPreparedEvent) {
            log.info("ApplicationPreparedEvent");
        }

    }

    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
    }

    public int getOrder() {
        return -2147483643;
    }

    public void onApplicationEnvironmentPreparedEvent(ApplicationEnvironmentPreparedEvent event) {
        String configPath = this.getConfigPath();
        this.loadBootappConfigProperties(configPath, event);
    }

    protected void loadBootappConfigProperties(String configPath, ApplicationEnvironmentPreparedEvent event) {
        File path = this.getConfigFile(configPath);
        String[] files = path.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".properties");
            }
        });
        if (files != null) {
            String[] var5 = files;
            int var6 = files.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String file = var5[var7];
                Properties properties = this.loadConfigProperties(configPath + "/" + file);
                PropertySource propertySource = new PropertiesPropertySource(file, properties);
                event.getEnvironment().getPropertySources().addBefore("systemEnvironment", propertySource);
                if (file.equals("log.properties")) {
                    System.getProperties().putAll(properties);
                }
            }
        }

    }

    private File getConfigFile(String configPath) {
        File path = null;

        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(configPath);
            path = resource.getFile();
            return path;
        } catch (IOException var5) {
            log.error(var5.getMessage());
            throw new RuntimeException("读取配置文件错误");
        }
    }

    private String getConfigPath() {
        Properties configResource = null;

        try {
            configResource = PropertiesLoaderUtils.loadAllProperties("config/bootapp.conf");
            if (configResource.size() == 0) {
                configResource = PropertiesLoaderUtils.loadAllProperties("config/defaultbootapp.conf");
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        String configPath = configResource.getProperty("config.path");
        return configPath;
    }

    private Properties loadConfigProperties(String configPath) {
        Properties properties = null;

        try {
            Resource resource = new UrlResource(configPath);
            properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties;
        } catch (Exception var4) {
            log.error("Can not load Bootapp Config properties! {}", ExceptionUtils.getStackTrace(var4));
            throw new Error(var4.getMessage());
        }
    }

    public void loadSystemProperties() {
        String file = "cloud.properties";
        Properties properties = null;

        try {
            String configPath = this.getConfigPath();
            properties = this.loadConfigProperties(configPath + "/" + file);
            new PropertiesPropertySource(file, properties);
            System.getProperties().putAll(properties);
        } catch (Exception var5) {
            log.error("Can not load cloud.properties Config properties! {}", ExceptionUtils.getStackTrace(var5));
            throw new Error(var5.getMessage());
        }
    }
}
