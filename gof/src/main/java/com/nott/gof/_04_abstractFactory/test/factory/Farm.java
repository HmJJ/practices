package com.nott.gof._04_abstractFactory.test.factory;

import com.nott.gof._04_abstractFactory.test.product.Animal;
import com.nott.gof._04_abstractFactory.test.product.Plant;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 11:43
 * @Modified By:
 **/
public interface Farm {

    public Animal newAnimal();
    public Plant newPlant();

}
