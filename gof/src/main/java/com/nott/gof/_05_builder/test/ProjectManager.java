package com.nott.gof._05_builder.test;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/22 16:16
 * @Modified By:
 **/
public class ProjectManager {

    private Decorator builder;
    public ProjectManager(Decorator builder) {
        this.builder = builder;
    }

    public Parlour decorate() {
        builder.buildWall();
        builder.buildTV();
        builder.buildSofa();
        return builder.getResult();
    }

}
