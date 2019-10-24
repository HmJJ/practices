package com.nott.gof._08_bridge.test;

import com.nott.gof.utils.ReadXML;
import com.nott.gof.utils.ShowImage;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/24 15:32
 * @Modified By:
 **/
public class BagManage {

    public static void main(String[] args) {
        Color color;
        Bag bag;
        color = (Color) ReadXML.getObject("com.nott.gof._08_bridge.test", "bridge.xml", 0);
        bag = (Bag) ReadXML.getObject("com.nott.gof._08_bridge.test", "bridge.xml", 1);
        bag.setColor(color);
        String name = bag.getName();
        ShowImage.show("桥接模式测试", name + ".jpg", "女士皮包");
    }

}
