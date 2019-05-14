package com.nott.thread_pt.code.pt_1.threads;

import com.nott.thread_pt.code.pt_1.enums.Store;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/5/14 14:23
 * @Modified By:
 **/
@Slf4j
public class Consume implements Runnable {

    Store store;
    public Consume(Store store){
        this.store = store;
    }

    /**
     * 消费者从店员取产品
     */
    public synchronized void run() {
        store.consume();
    }

}
