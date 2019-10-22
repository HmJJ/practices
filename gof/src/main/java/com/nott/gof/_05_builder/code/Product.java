package com.nott.gof._05_builder.code;

/**
 * @Author: wangjun
 * @Description: 产品角色
 * @Date: created in 2019/10/22 15:29
 * @Modified By:
 **/
public class Product {

    private String partA;
    private String partB;
    private String partC;

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    public void show() {
        System.out.println("Detail: " + partA + ", " + partB + ", " + partC);
    }
}
