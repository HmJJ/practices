package com.nott.gof.singleton;

import javax.swing.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/16 12:10
 * @Modified By:
 **/
public class HungrySingleton extends JPanel {

    /**
     * 饿汉式单例在类创建的同时j就已经创建好一个静态的对象供系统使用，以后不再改变，
     * 所以线程是安全的，可以直接yo用于多线程。
     */
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

        JLabel l1 = new JLabel(new ImageIcon("resources/images/Bajie.jpg"));
        this.add(l1);

    };
    public static HungrySingleton getInstance() {
        return instance;
    }

}
