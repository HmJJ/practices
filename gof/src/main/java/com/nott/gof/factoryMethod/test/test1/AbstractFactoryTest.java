package com.nott.gof.factoryMethod.test.test1;

import com.nott.gof.factoryMethod.code.AbstractFactory;
import com.nott.gof.factoryMethod.code.Product;
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
            af = (AbstractFactory) ReadXML1.getObject();
            a = af.newProduct();
            a.show();
        }catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

}
