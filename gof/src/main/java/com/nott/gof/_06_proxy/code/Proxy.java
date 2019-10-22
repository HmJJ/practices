package com.nott.gof._06_proxy.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 17:53
 * @Modified By:
 **/
public class Proxy implements Subject {

    private RealSubject realSubject;
    public void Request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.Request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }

}
