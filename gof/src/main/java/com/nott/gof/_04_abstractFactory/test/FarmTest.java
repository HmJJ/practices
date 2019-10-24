package com.nott.gof._04_abstractFactory.test;

import com.nott.gof._04_abstractFactory.test.factory.Farm;
import com.nott.gof._04_abstractFactory.test.product.Animal;
import com.nott.gof._04_abstractFactory.test.product.Plant;
import com.nott.gof.utils.ReadXML;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 10:37
 * @Modified By:
 **/
public class FarmTest {
    public static void main(String[] args) {
        try {
            Farm f;
            Animal a;
            Plant p;
            f = (Farm) ReadXML.getObject("com.nott.gof._04_abstractFactory.test.factory", "abstractFactory.xml", 0);
            a = f.newAnimal();
            p = f.newPlant();
            a.show();
            p.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
