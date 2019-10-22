package com.nott.gof._05_builder.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 16:15
 * @Modified By:
 **/
public class ConcreteDecorator2 extends Decorator {

    public void buildWall() {
        product.setWall("w2");
    }

    public void buildTV() {
        product.setTV("TV2");
    }

    public void buildSofa() {
        product.setSofa("sf2");
    }
}
