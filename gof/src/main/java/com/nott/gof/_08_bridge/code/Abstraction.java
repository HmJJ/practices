package com.nott.gof._08_bridge.code;

/**
 * @Author: wangjun
 * @Description: 抽象化角色
 * @Date: created in 2019/10/24 15:07
 * @Modified By:
 **/
public abstract class Abstraction {

    protected Implementor imple;
    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }
    public abstract void Operation();

}
