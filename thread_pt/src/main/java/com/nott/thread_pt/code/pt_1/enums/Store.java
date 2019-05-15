package com.nott.thread_pt.code.pt_1.enums;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/5/14 14:35
 * @Modified By:
 **/

@Slf4j
public class Store {

    private int output;
    private List<Integer> inventory;
    private int MAX_PRODUCT;
    private int MIN_PRODUCT;

    public Store() {}
    public Store(int output, int MAX_PRODUCT, int MIN_PRODUCT) {
        this.output = output;
        this.inventory = new ArrayList<>();
        this.MAX_PRODUCT = MAX_PRODUCT;
        this.MIN_PRODUCT = MIN_PRODUCT;
    }


    public synchronized void consume() {
        if (inventory.size() < MAX_PRODUCT) {
            try {
                wait();
                log.info("缺货，请等待厂家生产");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            log.info("商店卖出了第" + inventory.get(0) + "个产品.");
            inventory.remove(0);
        }catch (Exception e) {
            e.printStackTrace();
        }
        notifyAll();
    }

    public synchronized void produce() {
        if (inventory.size() >= MAX_PRODUCT) {
            try {
                wait();
                log.info("库存充足，请稍后再生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        output++;
        inventory.add(output);
        log.info("厂家生产第" + output + "个产品");
        notifyAll();
    }


    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public List<Integer> getInventory() {
        return inventory;
    }

    public void setInventory(List<Integer> inventory) {
        this.inventory = inventory;
    }

    public int getMAX_PRODUCT() {
        return MAX_PRODUCT;
    }

    public void setMAX_PRODUCT(int MAX_PRODUCT) {
        this.MAX_PRODUCT = MAX_PRODUCT;
    }

    public int getMIN_PRODUCT() {
        return MIN_PRODUCT;
    }

    public void setMIN_PRODUCT(int MIN_PRODUCT) {
        this.MIN_PRODUCT = MIN_PRODUCT;
    }
}
