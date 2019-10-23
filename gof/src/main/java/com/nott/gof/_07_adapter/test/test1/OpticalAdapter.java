package com.nott.gof._07_adapter.test.test1;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:45
 * @Modified By:
 **/
public class OpticalAdapter implements Motor {

    private OpticalMotor motor;
    public OpticalAdapter() {
        this.motor = new OpticalMotor();
    }
    public void drive() {
        motor.opticalDrive();
    }

}
