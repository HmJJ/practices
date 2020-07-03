package com.nott.security.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:33
 * @Modified By:
 **/
@Service
public class StringRedisService {

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    public StringRedisService() {}

    public void set(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, long seconds) {
        this.redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        this.redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public String get(String key) {
        return (String)this.redisTemplate.opsForValue().get(key);
    }

    public Boolean hasKey(String key) {
        return this.redisTemplate.hasKey(key);
    }

    public Long increment(String key, Long times) {
        if (times == null) {
            times = 1L;
        }

        Long value = this.redisTemplate.boundValueOps(key).increment(times);
        return value;
    }
}
