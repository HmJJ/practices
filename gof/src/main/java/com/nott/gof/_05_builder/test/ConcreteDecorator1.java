package com.nott.gof._05_builder.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 16:14
 * @Modified By:
 **/
public class ConcreteDecorator1 extends Decorator {

    public void buildWall() {
        product.setWall("w1");
    }

    public void buildTV() {
        product.setTV("TV1");
    }

    public void buildSofa() {
        product.setSofa("sf1");
    }
}
