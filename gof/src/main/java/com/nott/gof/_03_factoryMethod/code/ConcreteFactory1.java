package com.nott.gof._03_factoryMethod.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 16:29
 * @Modified By:
 **/
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public Product newProduct() {
        System.out.println("具体工厂1生成->具体产品1...");
        return new ConcreteProduct1();
    }
}
