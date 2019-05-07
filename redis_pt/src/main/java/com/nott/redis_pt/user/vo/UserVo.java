package com.nott.redis_pt.user.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class UserVo {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    @Getter
    @Setter
    private boolean delete;

    @Getter
    @Setter
    private Date createDate;

    @Getter
    @Setter
    private Date modifyDate;

    @Getter
    @Setter
    private Integer pageSize;

    @Getter
    @Setter
    private Integer pageNum;

    public String toString() {
        return "UserVO[ID:" + id + ", NAME:" + name + ". AGE:" + age + "]";
    }

}
