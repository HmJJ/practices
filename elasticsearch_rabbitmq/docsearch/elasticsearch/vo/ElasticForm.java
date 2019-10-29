package com.wayonsys.ecm.docsearch.elasticsearch.vo;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/15 18:47
 * @Modified By:
 **/
public class ElasticForm {
    protected MultipartFile multipartFile;
    protected Map<String, Object> map;

    public ElasticForm() {}
    public ElasticForm(MultipartFile file, Map<String, Object> map) {
        this.multipartFile = file;
        this.map = map;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
