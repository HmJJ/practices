package com.nott.gof._02_prototype.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 11:27
 * @Modified By:
 **/
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = (Realizetype) obj1.clone();
        System.out.println("obj1 == obj2?" + (obj1 == obj2));
    }

}
