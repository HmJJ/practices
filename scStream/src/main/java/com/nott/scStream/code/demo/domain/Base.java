package com.nott.scStream.code.demo.domain;

import io.searchbox.annotations.JestId;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/20 9:39
 * @Modified By:
 **/
@MappedSuperclass
public abstract class Base implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Long id;
    protected String esIndex;
    protected String esType;
    @JestId
    protected String documentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEsIndex() {
        return esIndex;
    }

    public void setEsIndex(String esIndex) {
        this.esIndex = esIndex;
    }

    public String getEsType() {
        return esType;
    }

    public void setEsType(String esType) {
        this.esType = esType;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
