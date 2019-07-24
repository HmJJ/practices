package com.nott.gof.prototype.test.test02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 12:01
 * @Modified By:
 **/
public class Citation implements Cloneable {

    private static final Logger logger = LoggerFactory.getLogger(Citation.class);

    private String name;
    private String info;
    private String college;

    public Citation(String name, String info, String college) {
        this.name = name;
        this.info = info;
        this.college = college;
    }

    public void display() {
        System.out.println(name + info + college);
    }

    public Object clone() throws CloneNotSupportedException {
        logger.info("奖状拷贝成功！");
        return (Citation)super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
