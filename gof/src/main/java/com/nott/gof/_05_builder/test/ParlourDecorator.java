package com.nott.gof._05_builder.test;

import com.nott.gof.utils.ReadXML;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 16:18
 * @Modified By:
 **/
public class ParlourDecorator {

    public static void main(String[] args) {
        try {
            Decorator d;
            d = (Decorator) ReadXML.getObject("com.nott.gof._05_builder.test", "builder.xml", 0);
            ProjectManager m = new ProjectManager(d);
            Parlour p = m.decorate();
            p.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
