package com.nott.gof._08_bridge.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/24 15:31
 * @Modified By:
 **/
public class Wallet extends Bag {

    public String getName() {
        return color.getColor() + "Wallet";
    }

}
