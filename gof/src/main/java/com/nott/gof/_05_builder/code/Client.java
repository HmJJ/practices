package com.nott.gof._05_builder.code;

/**
 * @Author: wangjun
 * @Description: 客户类
 * @Date: created in 2019/10/22 15:49
 * @Modified By:
 **/
public class Client {

    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }

}
