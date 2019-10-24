package com.nott.gof._08_bridge.code;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/24 15:09
 * @Modified By:
 **/
public class RefinedAbstraction extends Abstraction {

    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }
    public void Operation() {
        System.out.println("扩展抽象化（Refined Abstraction）角色被访问");
        imple.OperationImpl();
    }

}
