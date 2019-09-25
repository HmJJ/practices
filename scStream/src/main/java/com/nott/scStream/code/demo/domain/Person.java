package com.nott.scStream.code.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/29 17:49
 * @Modified By:
 **/
@Entity
@Table(name = "person")
public class Person extends Base {

    private String name;
    private String sex;
    private Integer age;
    private String remark;

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                ", esIndex='" + esIndex + '\'' +
                ", esType='" + esType + '\'' +
                '}';
    }
}
