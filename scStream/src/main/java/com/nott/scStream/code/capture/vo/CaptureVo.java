package com.nott.scStream.code.capture.vo;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/30 16:49
 * @Modified By:
 **/
public class CaptureVo {

    private String url;
    private List<String> params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
