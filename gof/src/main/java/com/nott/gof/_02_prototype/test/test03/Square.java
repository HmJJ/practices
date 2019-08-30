package com.nott.gof._02_prototype.test.test03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 13:50
 * @Modified By:
 **/
public class Square implements Shape {

    public static final Logger logger = LoggerFactory.getLogger(Square.class);

    @Override
    public Object clone() {
        Square s = null;
        try {
            s = (Square) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.info("拷贝正方形失败！");
        }
        return s;
    }

    @Override
    public void countArea() {

        int a = 0;
        System.out.println("这是一个正方形，请输入它的边长：");
        Scanner input = new Scanner(System.in);
        a = input.nextInt();
        System.out.println("该正方形的面积为：" + a * a + "\n");
    }
}
