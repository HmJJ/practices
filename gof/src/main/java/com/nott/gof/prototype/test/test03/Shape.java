package com.nott.gof.prototype.test.test03;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 13:45
 * @Modified By:
 **/
public interface Shape extends Cloneable {

    public Object clone();
    public void countArea();

}
