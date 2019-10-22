package com.nott.gof._04_abstractFactory.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 10:02
 * @Modified By:
 **/
public class ConcreteFactory1 implements AbstractFactory {

    public Product1 newProduct1() {
        System.out.println("具体工厂1生成->具体产品1...");
        return new ConcreteProduct1();
    }

    public Product2 newProduct2() {
        System.out.println("具体工厂2生成->具体产品2...");
        return new ConcreteProduct2();
    }
}
