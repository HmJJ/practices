package com.nott.security.common.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 11:29
 * @Modified By:
 **/
@Configuration
@EnableCaching
public class CacheConfiguration {

    private static final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.database:0}")
    private int database;
    @Value("${redis.pool.max-wait:-1}")
    private int maxWait;
    @Value("${redis.pool.max-idle:5}")
    private int maxIdle;
    @Value("${redis.pool.min-idle:2}")
    private int minIdle;
    @Value("${redis.pool.max-active:20}")
    private int maxTotal;
    @Value("${redis.pool.shutdown:100}")
    private int shutdown;
    @Value("${redis.pool.timeout:3000}")
    private int timeout;

    public CacheConfiguration() {}

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                Object[] var5 = params;
                int var6 = params.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    Object obj = var5[var7];
                    sb.append(obj.toString());
                }

                return sb.toString();
            }
        };
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(this.host);
        redisConfig.setPort(this.port);
        redisConfig.setDatabase(this.database);
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisConfig, this.getPoolConfig());
        return factory;
    }

    @Bean
    public LettucePoolingClientConfiguration getPoolConfig() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(this.maxTotal);
        config.setMaxWaitMillis((long)this.maxWait);
        config.setMaxIdle(this.maxIdle);
        config.setMinIdle(this.minIdle);
        LettucePoolingClientConfiguration pool =
                LettucePoolingClientConfiguration.builder()
                        .poolConfig(config)
                        .commandTimeout(Duration.ofMillis((long)this.timeout))
                        .shutdownTimeout(Duration.ofMillis((long)this.shutdown))
                        .build();
        return pool;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1L));
        Map<String, RedisCacheConfiguration> cacheNamesConfigurationMap = new HashMap<>();
        cacheNamesConfigurationMap.put("tenSecondsTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(10L)));
        cacheNamesConfigurationMap.put("minuteTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(60L)));
        cacheNamesConfigurationMap.put("fiveMinuteTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(300L)));
        cacheNamesConfigurationMap.put("tenMinuteTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(600L)));
        cacheNamesConfigurationMap.put("thirtyMinuteTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(1800L)));
        cacheNamesConfigurationMap.put("hourTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1L)));
        cacheNamesConfigurationMap.put("dayTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1L)));
        cacheNamesConfigurationMap.put("monthTerm", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(30L)));
        Set<String> cacheNames = new HashSet();
        cacheNames.add("tenSecondsTerm");
        cacheNames.add("minuteTerm");
        cacheNames.add("fiveMinuteTerm");
        cacheNames.add("tenMinuteTerm");
        cacheNames.add("thirtyMinuteTerm");
        cacheNames.add("hourTerm");
        cacheNames.add("dayTerm");
        cacheNames.add("monthTerm");
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(configuration)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(cacheNamesConfigurationMap)
                .build();
        return cacheManager;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        template.setConnectionFactory(factory);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

}
