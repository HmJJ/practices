package com.nott.gof._04_abstractFactory.test.product;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 10:49
 * @Modified By:
 **/
public class Horse implements Animal {

    JScrollPane sp;
    JFrame jf = new JFrame("抽象工厂模式测试");

    public Horse() {
        Container contenPane = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：马"));
        sp = new JScrollPane(p1);
        contenPane.add(sp, BorderLayout.CENTER);
        JLabel l1 = new JLabel(new ImageIcon("src/main/resources/images/A_Horse.jpg"));
        p1.add(l1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show() {
        jf.setVisible(true);
    }
}
