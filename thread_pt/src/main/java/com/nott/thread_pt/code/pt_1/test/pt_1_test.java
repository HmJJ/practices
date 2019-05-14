package com.nott.thread_pt.code.pt_1.test;

import com.nott.thread_pt.boot.ThreadPtApplication;
import com.nott.thread_pt.code.pt_1.enums.Store;
import com.nott.thread_pt.code.pt_1.threads.Consume;
import com.nott.thread_pt.code.pt_1.threads.Produce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/5/14 10:45
 * @Modified By:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ThreadPtApplication.class)
public class pt_1_test {


    @Test
    public void test_pt_1(){

        Store store = new Store(0, 15, 1);

        Produce produce = new Produce(store);
        Consume consume = new Consume(store);

        Thread producer = new Thread(produce);
        Thread customer = new Thread(consume);

        producer.start();
        customer.start();
    }

}
