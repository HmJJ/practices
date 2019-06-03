package com.nott.io.code.common.map;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/12/27 22:26
 * @Modified By:
 */
public class StringAbstractMap implements Map<String, Object>, Serializable {

    private static final long serialVersionUID = 1L;
    private final Map<String, Object> map;

    public StringAbstractMap(int initialCapacity, boolean ordered) {
        if (ordered) {
            this.map = new LinkedHashMap(initialCapacity);
        } else {
            this.map = new HashMap(initialCapacity);
        }
    }

    public StringAbstractMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return this.map.get(key);
    }



    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        this.map.putAll(m);
    }

    @Override
    public void clear() {
        this.map.clear();
    }

    @Override
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return this.map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return this.map.getOrDefault(key, defaultValue);
    }


    @Override
    public Object putIfAbsent(String key, Object value) {
        return this.map.putIfAbsent(key, value);
    }







}
