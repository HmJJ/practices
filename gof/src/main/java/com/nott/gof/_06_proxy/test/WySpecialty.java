package com.nott.gof._06_proxy.test;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: wangjun
 * @Description: 真实主题
 * @Date: created in 2019/10/22 18:24
 * @Modified By:
 **/
public class WySpecialty extends JFrame implements Specialty {

    private static final long serialVersionUID = 1L;
    public WySpecialty() {
        super("韶关代理婺源特产测试");
        this.setLayout(new GridLayout(1, 1));
        JLabel l1 = new JLabel(new ImageIcon("src/main/resources/images/WuyuanSpecialty.jpg"));
        this.add(l1);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display() {
        this.setVisible(true);
    }

}
