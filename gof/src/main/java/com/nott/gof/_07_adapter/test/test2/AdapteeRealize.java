package com.nott.gof._07_adapter.test.test2;

/**
 * @Author: wangjun
 * @Description: 适配者实现
 * @Date: created in 2019/10/23 15:52
 * @Modified By:
 **/
public class AdapteeRealize implements TwoWayAdaptee {

    public void specificRequest() {
        System.out.println("适配者代码被调用！");
    }

}
