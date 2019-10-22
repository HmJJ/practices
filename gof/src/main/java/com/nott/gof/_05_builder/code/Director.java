package com.nott.gof._05_builder.code;

/**
 * @Author: wangjun
 * @Description: 指挥者
 * @Date: created in 2019/10/22 15:43
 * @Modified By:
 **/
public class Director {

    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }

}
