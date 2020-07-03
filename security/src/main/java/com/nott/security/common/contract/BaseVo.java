package com.nott.security.common.contract;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/3 14:35
 * @Modified By:
 **/
public class BaseVo<T> implements Serializable {
    @JsonIgnore
    protected Long userId;
    protected Long id;

    public BaseVo() {
    }

    public T toDomain() {
        T t = null;
        Class clazz = this.getTypeParameterClass();

        try {
            t = (T) clazz.newInstance();
        } catch (InstantiationException var4) {
            var4.printStackTrace();
        } catch (IllegalAccessException var5) {
            var5.printStackTrace();
        }

        BeanUtils.copyProperties(this, t);
        return t;
    }

    protected Class<T> getTypeParameterClass() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType)type;
        return (Class)paramType.getActualTypeArguments()[0];
    }

    @JsonIgnore
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
