package com.nott.gof._03_factoryMethod.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 16:27
 * @Modified By:
 **/
public class ConcreteProduct2 implements Product {
    @Override
    public void show() {
        System.out.println("具体产品2显示...");
    }
}
