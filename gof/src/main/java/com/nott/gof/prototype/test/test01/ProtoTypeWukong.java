package com.nott.gof.prototype.test.test01;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 11:56
 * @Modified By:
 **/
public class ProtoTypeWukong {

    public static void main(String[] args) {
        JFrame jf = new JFrame("原型模式测试");
        jf.setLayout(new GridLayout(1,2));
        Container container = jf.getContentPane();
        SunWukong obj1 = new SunWukong();
        container.add(obj1);
        SunWukong obj2 = (SunWukong) obj1.clone();
        container.add(obj2);
        SunWukong obj3 = (SunWukong) obj2.clone();
        container.add(obj3);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
