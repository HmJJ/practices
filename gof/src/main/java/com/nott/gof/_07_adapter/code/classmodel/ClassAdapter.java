package com.nott.gof._07_adapter.code.classmodel;

import com.nott.gof._07_adapter.code.Adaptee;
import com.nott.gof._07_adapter.code.Target;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:32
 * @Modified By:
 **/
public class ClassAdapter extends Adaptee implements Target {

    public void request() {
        specificRequest();
    }

}
