package com.nott.gof.prototype.test.test03;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 13:58
 * @Modified By:
 **/
public class ProtoTypeShape {

    public static void main(String[] args) {
        ProtoTypeManager manager = new ProtoTypeManager();
        Shape obj1 = (Circle) manager.getShape("Circle");
        obj1.countArea();
        Shape obj2 = manager.getShape("Square");
        obj2.countArea();
    }

}
