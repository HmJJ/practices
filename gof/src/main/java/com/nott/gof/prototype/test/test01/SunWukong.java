package com.nott.gof.prototype.test.test01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 11:51
 * @Modified By:
 **/
public class SunWukong extends JPanel implements Cloneable {

    private static final long serialVersionUID = 5543049531872119328L;
    private static final Logger logger = LoggerFactory.getLogger(SunWukong.class);

    public SunWukong() {
        JLabel ll = new JLabel(new ImageIcon("src/main/resources/images/Wukong.jpg"));
        this.add(ll);
    }

    public Object clone() {
        SunWukong wukong = null;
        try {
            wukong = (SunWukong) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.info("悟空拷贝失败！");
        }
        return wukong;
    }

}
