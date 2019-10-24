package com.nott.gof._08_bridge.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/24 15:10
 * @Modified By:
 **/
public class BridgeTest {

    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.Operation();
    }

}
