package com.nott.gof._07_adapter.test.test1;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:44
 * @Modified By:
 **/
public class ElectricAdapter implements Motor {

    private ElectricMotor electricMotor;
    public ElectricAdapter() {
        this.electricMotor = new ElectricMotor();
    }
    public void drive() {
        electricMotor.electricDrive();
    }

}
