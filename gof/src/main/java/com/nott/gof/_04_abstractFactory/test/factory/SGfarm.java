package com.nott.gof._04_abstractFactory.test.factory;

import com.nott.gof._04_abstractFactory.test.product.Animal;
import com.nott.gof._04_abstractFactory.test.product.Cattle;
import com.nott.gof._04_abstractFactory.test.product.Plant;
import com.nott.gof._04_abstractFactory.test.product.Vegetables;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 11:43
 * @Modified By:
 **/
public class SGfarm implements Farm {

    public Animal newAnimal() {
        System.out.println("小牛出生！");
        return new Cattle();
    }

    public Plant newPlant() {
        System.out.println("蔬菜长成！");
        return new Vegetables();
    }

}
