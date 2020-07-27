package com.nott.external.code.external.service;

import com.alibaba.fastjson.JSONObject;
import com.nott.external.code.capture.service.CaptureService;
import com.nott.external.code.external.connect.ExternalConnector;
import com.nott.external.code.external.connect.vo.ConnectVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/27 14:47
 * @Modified By:
 **/
@Service
public class ExternalService {

    public static final Logger logger = LoggerFactory.getLogger(ExternalService.class);

    public static final String ISSUE_URL = "http://47.102.122.30:880/hazards/stat";
    public static final String RISK_ANALYSIS_URL = "http://47.102.122.30:880/risks/stat";

    @Autowired
    private CaptureService captureService;
    @Autowired
    private ExternalConnector externalConnector;

    public String getIssueInfo() {
        ConnectVo connectVo = new ConnectVo();
        connectVo.setType("getRiskInfo");
        String result = getUrlResult(connectVo);
        return result;
    }

    public String getRiskAnalysisInfo() {
        ConnectVo connectVo = new ConnectVo();
        connectVo.setType("getRiskAnalysisInfo");
        String result = getUrlResult(connectVo);
        return result;
    }

    private String getUrlResult(ConnectVo connectVo) {
        return externalConnector.execute(connectVo);
    }

}
