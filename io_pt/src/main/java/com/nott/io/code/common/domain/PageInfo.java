package com.nott.io.code.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: allen
 * @Description:
 * @Date: created in 2018/11/15 10:34
 * @Modified By:
 */
public class PageInfo<T> implements Serializable {

    protected int totalItems;
    protected List<T> list;


    public PageInfo(List<T> list, int totalItems) {
        this.list = list;
        this.totalItems = totalItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<T> getList() {
        return list;
    }


    @Override
    public String toString() {
        return "PageInfo{" + "total=" + totalItems + ", list size=" + list.size() + '}';
    }
}
