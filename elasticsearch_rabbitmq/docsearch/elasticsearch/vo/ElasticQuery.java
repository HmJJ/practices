package com.wayonsys.ecm.docsearch.elasticsearch.vo;

import java.util.Arrays;
import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/14 10:36
 * @Modified By:
 **/
public class ElasticQuery {

    // 库名
    protected String index = "doc";
    // 分页
    protected int page = 1;
    protected int size = 10;
    // 时间范围
    protected Map<String, Object> where;
    // 排序
    protected Map<String, Boolean> sortFieldToAsc;
    // 返回包含指定列和排除指定列
    protected String[] includeFields;
    protected String[] excludeFields;
    protected String[] matchFields;
    // 设置超时时间
    protected int timeOut = 3000;
    // 查询值
    protected String query;
    protected String id;
    protected String tenantId;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, Object> getWhere() {
        return where;
    }

    public void setWhere(Map<String, Object> where) {
        this.where = where;
    }

    public Map<String, Boolean> getSortFieldToAsc() {
        return sortFieldToAsc;
    }

    public void setSortFieldToAsc(Map<String, Boolean> sortFieldToAsc) {
        this.sortFieldToAsc = sortFieldToAsc;
    }

    public String[] getIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(String[] includeFields) {
        this.includeFields = includeFields;
    }

    public String[] getExcludeFields() {
        return excludeFields;
    }

    public void setExcludeFields(String[] excludeFields) {
        this.excludeFields = excludeFields;
    }

    public String[] getMatchFields() {
        return matchFields;
    }

    public void setMatchFields(String[] matchFields) {
        this.matchFields = matchFields;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "ElasticQuery{" +
                "index='" + index + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", where=" + where +
                ", sortFieldToAsc=" + sortFieldToAsc +
                ", includeFields=" + Arrays.toString(includeFields) +
                ", excludeFields=" + Arrays.toString(excludeFields) +
                ", matchFields=" + Arrays.toString(matchFields) +
                ", timeOut=" + timeOut +
                ", query='" + query + '\'' +
                ", id='" + id + '\'' +
                ", tenantId='" + tenantId + '\'' +
                '}';
    }
}
