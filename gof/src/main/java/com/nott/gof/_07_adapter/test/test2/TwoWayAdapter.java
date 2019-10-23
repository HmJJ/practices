package com.nott.gof._07_adapter.test.test2;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:53
 * @Modified By:
 **/
public class TwoWayAdapter implements TwoWayTarget, TwoWayAdaptee {

    private TwoWayTarget target;
    private TwoWayAdaptee adaptee;
    public TwoWayAdapter(TwoWayTarget target) {
        this.target = target;
    }
    public TwoWayAdapter(TwoWayAdaptee adaptee) {
        this.adaptee = adaptee;
    }
    public void request() {
        adaptee.specificRequest();
    }
    public void specificRequest() {
        target.request();
    }

}
