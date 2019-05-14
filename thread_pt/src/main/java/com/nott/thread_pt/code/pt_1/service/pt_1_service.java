package com.nott.thread_pt.code.pt_1.service;

import org.springframework.stereotype.Service;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/5/14 10:27
 * @Modified By:
 **/
@Service
public class pt_1_service implements Runnable {

    Object lock;
    public void run() {
        synchronized (lock) {
            //TODO
        }
    }
}
