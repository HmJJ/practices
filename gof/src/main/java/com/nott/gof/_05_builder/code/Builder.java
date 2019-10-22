package com.nott.gof._05_builder.code;

/**
 * @Author: wangjun
 * @Description: 抽象建造炸
 * @Date: created in 2019/10/22 15:39
 * @Modified By:
 **/
public abstract class Builder {

    protected Product product = new Product();
    public abstract void buildPartA();
    public abstract void buildPartB();
    public abstract void buildPartC();

    public Product getResult() {
        return product;
    }

}
