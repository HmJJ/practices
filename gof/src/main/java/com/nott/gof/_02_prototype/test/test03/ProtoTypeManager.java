package com.nott.gof._02_prototype.test.test03;

import java.util.HashMap;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 13:54
 * @Modified By:
 **/
public class ProtoTypeManager {

    private HashMap<String, Shape> manager = new HashMap<String, Shape>();
    public ProtoTypeManager() {
        manager.put("Circle", new Circle());
        manager.put("Square", new Square());
    }

    public void addShape(String key, Shape shape) {
        manager.put(key, shape);
    }

    public Shape getShape(String key) {
        Shape temp = manager.get(key);
        return (Shape) temp.clone();
    }

}
