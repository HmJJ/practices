package com.nott.gof._06_proxy.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 18:28
 * @Modified By:
 **/
public class SgProxy implements Specialty {

    private WySpecialty realSubject = new WySpecialty();
    public void display() {
        preRequest();
        realSubject.display();
        postRequest();
    }

    public void preRequest() {
        System.out.println("韶关代理婺源特产开始。");
    }

    public void postRequest() {
        System.out.println("韶关代理婺源特产结束。");
    }

}
