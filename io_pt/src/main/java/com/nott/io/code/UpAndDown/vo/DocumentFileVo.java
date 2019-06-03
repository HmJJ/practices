package com.nott.io.code.UpAndDown.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/6/3 14:55
 * @Modified By:
 **/
public class DocumentFileVo {

    protected Long documentId;
    protected String store;
    protected String fileName;
    protected Integer fileSize;
    protected String fileSuffix;
    @JsonFormat(pattern = ("yyyy-MM-dd HH:mm"))
    protected LocalDateTime fileTime;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public LocalDateTime getFileTime() {
        return fileTime;
    }

    public void setFileTime(LocalDateTime fileTime) {
        this.fileTime = fileTime;
    }

}
