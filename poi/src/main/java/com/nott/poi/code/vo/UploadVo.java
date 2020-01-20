package com.nott.poi.code.vo;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 11:20
 * @Modified By:
 **/
public class UploadVo {

    protected Long orderId;
    protected List<MultipartFile> files;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
