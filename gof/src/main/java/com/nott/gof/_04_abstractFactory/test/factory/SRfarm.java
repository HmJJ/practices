package com.nott.gof._04_abstractFactory.test.factory;

import com.nott.gof._04_abstractFactory.test.product.Animal;
import com.nott.gof._04_abstractFactory.test.product.Fruitage;
import com.nott.gof._04_abstractFactory.test.product.Horse;
import com.nott.gof._04_abstractFactory.test.product.Plant;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 11:45
 * @Modified By:
 **/
public class SRfarm implements Farm{

    public Animal newAnimal() {
        System.out.println("小马出生！");
        return new Horse();
    }

    public Plant newPlant() {
        System.out.println("水果长成！");
        return new Fruitage();
    }

}
