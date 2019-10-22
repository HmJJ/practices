package com.nott.gof.utils;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 16:37
 * @Modified By:
 **/
public class ShowImage {

    public static void show(String jframTitle, String picName, String borderTitle) {
        JFrame jf = new JFrame(jframTitle);
        Container contentPane = jf.getContentPane();
        JPanel p = new JPanel();
        JScrollPane sp = new JScrollPane(p);
        JLabel l = new JLabel(new ImageIcon("src/main/resources/images/" + picName));
        p.setLayout(new GridLayout(1, 1));
        p.setBorder(BorderFactory.createTitledBorder(borderTitle));
        p.add(l);
        contentPane.add(sp, BorderLayout.CENTER);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
