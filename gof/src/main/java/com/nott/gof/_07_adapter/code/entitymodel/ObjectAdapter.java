package com.nott.gof._07_adapter.code.entitymodel;

import com.nott.gof._07_adapter.code.Target;
import com.nott.gof._07_adapter.code.Adaptee;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:36
 * @Modified By:
 **/
public class ObjectAdapter implements Target {

    private Adaptee adaptee;
    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    public void request() {
        adaptee.specificRequest();
    }

}
