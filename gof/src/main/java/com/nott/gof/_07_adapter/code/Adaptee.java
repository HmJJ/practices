package com.nott.gof._07_adapter.code;

/**
 * @Author: wangjun
 * @Description: 适配者接口
 * @Date: created in 2019/10/23 15:32
 * @Modified By:
 **/
public class Adaptee {

    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }

}
