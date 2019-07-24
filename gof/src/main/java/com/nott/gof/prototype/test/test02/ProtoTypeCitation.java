package com.nott.gof.prototype.test.test02;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 13:38
 * @Modified By:
 **/
public class ProtoTypeCitation {

    public static void main(String[] args) throws CloneNotSupportedException {
        Citation obj1 = new Citation("张三", "同学：在2019学年第一学期中表现优秀，被评为三好学生。", "泰和中学");
        obj1.display();
        Citation obj2 = (Citation) obj1.clone();
        obj2.setName("李四");
        obj2.display();
    }

}
