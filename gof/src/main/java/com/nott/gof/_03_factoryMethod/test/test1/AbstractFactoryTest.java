package com.nott.gof._03_factoryMethod.test.test1;

import com.nott.gof._03_factoryMethod.code.AbstractFactory;
import com.nott.gof._03_factoryMethod.code.Product;
import com.nott.gof.utils.ReadXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 16:31
 * @Modified By:
 **/
public class AbstractFactoryTest {

    public static final Logger logger = LoggerFactory.getLogger(AbstractFactory.class);

    public static void main(String[] args) {

        try {
            Product a;
            AbstractFactory af;
            af = (AbstractFactory) ReadXML.getObject("com.nott.gof._03_factoryMethod.code", "factoryMethod.xml", 0);
            a = af.newProduct();
            a.show();
        }catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

}
