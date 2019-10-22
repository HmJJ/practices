package com.nott.gof._05_builder.test;

import com.nott.gof.utils.ShowImage;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 15:54
 * @Modified By:
 **/
public class Parlour {

    private String wall;
    private String TV;
    private String sofa;

    public void setWall(String wall) {
        this.wall = wall;
    }

    public void setTV(String TV) {
        this.TV = TV;
    }

    public void setSofa(String sofa) {
        this.sofa = sofa;
    }

    public void show() {
        String parlour = wall + TV + sofa;
        ShowImage.show("建造者模式测试", parlour + ".jpg", "客厅");
    }

}
