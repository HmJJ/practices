package com.nott.io.code.mongodb;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wangjun
 * @Description:
 * @Date: created in 2019/4/29
 * @Modified By:
 */
public class MongoFile {

    private String name;
    private String mimeType;
    private Map<String, Object> metadata = new HashMap();
    private byte[] content;

    public MongoFile(String fileName, String mimeType, byte[] content) {
        this.name = fileName;
        this.mimeType = mimeType;
        this.content = content;
    }

    public void put(String key, Object value) {
        this.metadata.put(key, value);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    public byte[] getContent() {
        return this.content;
    }

}
