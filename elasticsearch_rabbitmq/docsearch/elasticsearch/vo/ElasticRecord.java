package com.wayonsys.ecm.docsearch.elasticsearch.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/17 9:43
 * @Modified By:
 **/
public class ElasticRecord {
    protected String id;
    protected String name;
    protected String store;
    protected String suffix;
    protected String creator;
    protected String createTime;
    protected String content;
    protected JSONObject highlight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JSONObject getHighlight() {
        return highlight;
    }

    public void setHighlight(JSONObject highlight) {
        this.highlight = highlight;
    }
}
