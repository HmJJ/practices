package com.nott.gof.singleton.test;

import com.nott.gof.singleton.code.HungrySingleton;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/16 13:59
 * @Modified By:
 **/
public class HungrySingletonBoot {

    public static void main(String[] args) {

        JFrame jf = new JFrame("饿汉单例模式测试");
        jf.setLayout(new GridLayout(1,2));
        Container contentPane = jf.getContentPane();
        HungrySingleton bajie = HungrySingleton.getInstance();
        contentPane.add(bajie);
        HungrySingleton bajie2 = HungrySingleton.getInstance();
        contentPane.add(bajie2);
        if (bajie == bajie2) {
            System.out.println("他们是同一个人");
        } else {
            System.out.println("他们不是同一个人");
        }
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
