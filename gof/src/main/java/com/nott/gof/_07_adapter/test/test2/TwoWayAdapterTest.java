package com.nott.gof._07_adapter.test.test2;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/23 15:55
 * @Modified By:
 **/
public class TwoWayAdapterTest {

    public static void main(String[] args) {
        System.out.println("目标通过双向适配器访问适配者：");
        TwoWayAdaptee adaptee = new AdapteeRealize();
        TwoWayTarget target = new TwoWayAdapter(adaptee);
        target.request();
        System.out.println("------------------------------");
        System.out.println("目标通过双向适配器访问适配者：");
        target = new TargetRealize();
        adaptee = new TwoWayAdapter(target);
        adaptee.specificRequest();
    }

}
