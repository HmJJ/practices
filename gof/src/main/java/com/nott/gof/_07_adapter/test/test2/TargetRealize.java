package com.nott.gof._07_adapter.test.test2;

/**
 * @Author: wangjun
 * @Description: 目标实现
 * @Date: created in 2019/10/23 15:51
 * @Modified By:
 **/
public class TargetRealize implements TwoWayTarget {

    public void request() {
        System.out.println("目标代码被调用！");
    }

}
