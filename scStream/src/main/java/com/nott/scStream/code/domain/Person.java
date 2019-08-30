package com.nott.scStream.code.domain;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/29 17:49
 * @Modified By:
 **/
public class Person {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

}
