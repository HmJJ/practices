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
    @Column(name = "id", length = 32)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name", length = 32)
    @Getter
    @Setter
    private String name;

    @Column(name = "age", length = 4)
    @Getter
    @Setter
    private Integer age;

    @Column(name = "delete", length = 2)
    @Getter
    @Setter
    private boolean delete = false;

    @Column(name = "create_date", length = 16)
    @Getter
    @Setter
    private Date createDate = new Date();

    @Column(name = "modify_date", length = 16)
    @Getter
    @Setter
    private Date modifyDate = new Date();

}
