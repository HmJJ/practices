package com.nott.gof._07_adapter.code.entitymodel;

import com.nott.gof._07_adapter.code.Adaptee;
import com.nott.gof._07_adapter.code.Target;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:37
 * @Modified By:
 **/
public class ObjectAdapterTest {

    public static void main(String[] args) {
        System.out.println("对象适配器模式测试：");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }

}
