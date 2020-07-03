package com.nott.security.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:30
 * @Modified By:
 **/
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisService() {}

    public Long increment(String key, String hashKey, Long times) {
        if (times == null) {
            times = 1L;
        }

        Long value = this.redisTemplate.opsForHash().increment(key, hashKey, times);
        return value;
    }

    public void set(String key, String hashKey, Object o) {
        this.redisTemplate.opsForHash().put(key, hashKey, o);
    }

    public void set(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        Object o = this.redisTemplate.opsForValue().get(key);
        return o;
    }

    public Object get(String key, String hashKey) {
        Object o = this.redisTemplate.opsForHash().get(key, hashKey);
        return o;
    }

    public Boolean hasKey(String key, String hashKey) {
        Boolean flag = this.redisTemplate.opsForHash().hasKey(key, hashKey);
        return flag;
    }

    public Boolean setExpire(String key, Long seconds) {
        Boolean flag = this.redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
        return flag;
    }

    public Boolean delete(String key) {
        Boolean flag = this.redisTemplate.delete(key);
        return flag;
    }
}
