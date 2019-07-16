package com.nott.gof.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/16 9:58
 * @Modified By:
 **/
public class LazySingleton {

    private static Logger logger = LoggerFactory.getLogger(LazySingleton.class);

    /**
     * volatile 和 synchronized能保证在多线程情况下的线程安全，
     * 但是每次访问都需要同步，会影响性能，消耗更多资源。
     */
    private static volatile LazySingleton instance = null;

    private LazySingleton() {
        System.out.println("出现了一个懒汉");
    }
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        } else {
            logger.info("LazySingletion 已经实例化过了");
            System.out.println("一个懒汉够了，不能再有懒汉了");
        }
        return instance;
    }

    public void getName() {
        System.out.println("懒汉");
    }

}
