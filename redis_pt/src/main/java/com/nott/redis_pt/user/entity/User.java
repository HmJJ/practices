package com.nott.redis_pt.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_USER")
public class User {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "age")
    @Getter
    @Setter
    private Integer age;

    @Column(name = "delete")
    @Getter
    @Setter
    private boolean delete = false;

    @Column(name = "create_date")
    @Getter
    @Setter
    private Date createDate = new Date();

    @Column(name = "modify_date")
    @Getter
    @Setter
    private Date modifyDate = new Date();

}
