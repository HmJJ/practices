package com.nott.external.code.external.connect.vo;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/4/29 18:24
 * @Modified By:
 **/
public class ConnectVo {

    private BusinessInfo businessInfo;
    private String operate;
    private List<ParamVo> params;
    private String type;
    private String statue;

    public BusinessInfo getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfo businessInfo) {
        this.businessInfo = businessInfo;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public List<ParamVo> getParams() {
        return params;
    }

    public void setParams(List<ParamVo> params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }
}
