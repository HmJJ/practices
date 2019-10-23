package com.nott.gof._07_adapter.code.classmodel;

import com.nott.gof._07_adapter.code.Target;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:33
 * @Modified By:
 **/
public class ClassAdapterTest {

    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }

}
