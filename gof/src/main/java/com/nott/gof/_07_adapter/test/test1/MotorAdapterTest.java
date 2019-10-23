package com.nott.gof._07_adapter.test.test1;

import com.nott.gof.utils.ReadXML;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:47
 * @Modified By:
 **/
public class MotorAdapterTest {

    public static void main(String[] args) {
        System.out.println("适配器模式测试：");
        Motor motor = (Motor) ReadXML.getObject("com.nott.gof._07_adapter.test.test1", "adapter.xml");
        motor.drive();
    }

}
