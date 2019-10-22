package com.nott.gof._05_builder.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 16:13
 * @Modified By:
 **/
public abstract class Decorator {

    protected Parlour product = new Parlour();
    public abstract void buildWall();
    public abstract void buildTV();
    public abstract void buildSofa();

    public Parlour getResult() {
        return product;
    }

}
