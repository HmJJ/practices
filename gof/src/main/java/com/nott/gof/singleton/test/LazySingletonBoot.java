package com.nott.gof.singleton.test;

import com.nott.gof.singleton.code.LazySingleton;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/16 13:50
 * @Modified By:
 **/
public class LazySingletonBoot {

    public static void main(String[] args) {

        LazySingleton lazySingleton = LazySingleton.getInstance();
        lazySingleton.getName();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        lazySingleton2.getName();
        if (lazySingleton == lazySingleton2) {
            System.out.println("同一个懒汉实例");
        }

    }

}
