package com.nott.thread_pt.code.pt_1.threads;

import com.nott.thread_pt.code.pt_1.enums.Store;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/5/14 14:23
 * @Modified By:
 **/
@Slf4j
public class Produce implements Runnable {

    Store store;
    public Produce(Store store){
        this.store = store;
    }

    /**
     * 生产者生产出来的产品交给店员
     */
    public synchronized void run() {
        store.produce();
    }

}
