package com.nott.gof._05_builder.code;

/**
 * @Author: wangjun
 * @Description: 具体建造者
 * @Date: created in 2019/10/22 15:41
 * @Modified By:
 **/
public class ConcreteBuilder extends Builder {

    public void buildPartA() {
        product.setPartA("建造 PartA");
    }

    public void buildPartB() {
        product.setPartB("建造 PartB");
    }

    public void buildPartC() {
        product.setPartC("建造 PartC");
    }
}
