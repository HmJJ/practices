package com.nott.external.code.external.connect;

import com.nott.external.code.external.connect.vo.ConnectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/4/29 17:56
 * @Modified By:
 **/
@Service
public class ExternalConnector {

    public static final Logger log = LoggerFactory.getLogger(ExternalConnector.class);

    @Autowired
    private Connector connector;

    public String execute(ConnectVo connectVo) {
        String result = connector.execute(connectVo.getType(), connectVo.getParams());
        return result;
    }

}
