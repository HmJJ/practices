package com.nott.gof._08_bridge.code;

/**
 * @Author: wangjun
 * @Description: 具体实现化角色
 * @Date: created in 2019/10/24 15:06
 * @Modified By:
 **/
public class ConcreteImplementorA implements Implementor {

    public void OperationImpl() {
        System.out.println("具体实现化（Concrete Implementor）角色被访问");
    }

}
