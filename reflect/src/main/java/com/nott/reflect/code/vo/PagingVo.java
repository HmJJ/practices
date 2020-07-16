package com.nott.reflect.code.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/16 18:33
 * @Modified By:
 **/
public class PagingVo implements Serializable {

    protected List<Long> groupIdList;

    public List<Long> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<Long> groupIdList) {
        this.groupIdList = groupIdList;
    }
}
