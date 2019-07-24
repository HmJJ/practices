package com.nott.gof.prototype.code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/7/24 11:24
 * @Modified By:
 **/
public class Realizetype implements Cloneable {

    public static final Logger logger = LoggerFactory.getLogger(Realizetype.class);

    Realizetype() {
        logger.info("具体原型创建成功！");
    }

    public Object clone() throws CloneNotSupportedException {
        logger.info("具体原型复制成功！");
        return (Realizetype)super.clone();
    }

}
