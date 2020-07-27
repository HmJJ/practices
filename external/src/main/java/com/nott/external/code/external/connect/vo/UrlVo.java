package com.nott.external.code.external.connect.vo;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/4/29 17:09
 * @Modified By:
 **/
public class UrlVo {

    private String type;
    private String remark;
    private String url;
    private String method;
    private List<ParamVo> params;
    private boolean needParams;

    public UrlVo(String type, String remark, String url, String method, boolean needParams, List<ParamVo> params) {
        this.type = type;
        this.remark = remark;
        this.method = method;
        this.url = url;
        this.needParams = needParams;
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<ParamVo> getParams() {
        return params;
    }

    public void setParams(List<ParamVo> params) {
        this.params = params;
    }

    public boolean isNeedParams() {
        return needParams;
    }

    public void setNeedParams(boolean needParams) {
        this.needParams = needParams;
    }
}
