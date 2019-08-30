package com.nott.gof._03_factoryMethod.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 16:30
 * @Modified By:
 **/
public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public Product newProduct() {
        System.out.println("具体工厂2生成->具体产品2...");
        return new ConcreteProduct2();
    }
}
