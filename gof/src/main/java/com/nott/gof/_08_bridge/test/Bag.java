package com.nott.gof._08_bridge.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/24 15:30
 * @Modified By:
 **/
public abstract class Bag {

    protected Color color;
    public void setColor(Color color) {
        this.color = color;
    }
    public abstract String getName();

}
