package com.nott.io.code.common.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/12/27 22:45
 * @Modified By:
 */
public class StringMap extends StringAbstractMap {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public StringMap() {
        super(DEFAULT_INITIAL_CAPACITY, false);
    }

    public StringMap(Map<String, Object> map) {
        super(map);
    }

    public StringMap(boolean ordered) {
        super(DEFAULT_INITIAL_CAPACITY, ordered);
    }

    public StringMap(int initialCapacity) {
        super(initialCapacity, false);
    }

    public StringMap(int initialCapacity, boolean ordered) {
        super(initialCapacity, ordered);

    }

    public String getString(String key) {
        Object value = this.get(key);
        return value == null ? null : value.toString();
    }

    public static void main(String[] args) {

    }

}
