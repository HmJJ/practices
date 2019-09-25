package com.nott.scStream.code.demo.vo;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 16:49
 * @Modified By:
 **/
public class PersonQuery {

    protected Long id;
    protected String name;
    protected String sex;
    protected Integer age;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
