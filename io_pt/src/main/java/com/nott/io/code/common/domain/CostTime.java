package com.nott.io.code.common.domain;

/**
 * Created by allen on 2017/1/4.
 */
public class CostTime {

    private transient long start;

    public static CostTime create() {
        CostTime costTime = new CostTime();
        costTime.start();
        return costTime;
    }

    public void start(){
        this.start = System.currentTimeMillis();
    }

    public long cost(){
        long result = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        return result;
    }
}
