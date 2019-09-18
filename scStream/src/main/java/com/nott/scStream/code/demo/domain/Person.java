package com.nott.scStream.code.demo.domain;

import javax.persistence.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/29 17:49
 * @Modified By:
 **/
@Entity
@Table(name = "person")
public class Person {

    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String remark;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", remark='" + remark + '\'' +
                '}';
    }
}
