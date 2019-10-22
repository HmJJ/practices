package com.nott.gof._06_proxy.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 17:54
 * @Modified By:
 **/
public class RealSubject implements Subject {

    public void Request() {
        System.out.println("访问真实主题方法。。。");
    }

}
