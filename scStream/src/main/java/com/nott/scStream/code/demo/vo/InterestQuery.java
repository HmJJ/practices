package com.nott.scStream.code.demo.vo;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 17:09
 * @Modified By:
 **/
public class InterestQuery {

    protected Long id;
    protected String name;
    protected Long personId;
    protected String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
