package com.nott.scStream.code.demo.domain;

import javax.persistence.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 16:43
 * @Modified By:
 **/
@Entity
@Table(name = "interest")
public class Interest {

    private Long id;
    private String name;
    private Long personId;
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

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", personId=" + personId +
                ", remark='" + remark + '\'' +
                '}';
    }
}
