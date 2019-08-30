package com.nott.gof._02_prototype.test.test03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 13:46
 * @Modified By:
 **/
public class Circle implements Shape {

    public static final Logger logger = LoggerFactory.getLogger(Circle.class);

    @Override
    public Object clone() {
        Circle c = null;
        try {
            c = (Circle) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.info("拷贝圆失败！");
        }
        return c;
    }

    @Override
    public void countArea() {

        int r = 0;
        System.out.println("这是一个圆，请输入圆的半径：");
        Scanner input = new Scanner(System.in);
        r = input.nextInt();
        System.out.println("该圆的面积为：" + 3.1415 * r * r + "\n");

    }
}
