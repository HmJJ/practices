package com.nott.gof._04_abstractFactory.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 10:03
 * @Modified By:
 **/
public class ConcreteProduct1 implements Product1 {

    @Override
    public void show() {
        System.out.println("具体产品1显示。。。");
    }
}
