package com.nott.external.code.external.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nott.external.code.external.service.ExternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/27 14:40
 * @Modified By:
 **/
@RestController
@RequestMapping("/external")
public class ExternalController {

    public static final Logger logger = LoggerFactory.getLogger(ExternalController.class);

    @Autowired
    private ExternalService externalService;

    @GetMapping("/getIssueInfo")
    public JSONObject getIssueInfo() {
        String result = externalService.getIssueInfo();
        JSONObject issueInfo = JSONObject.parseObject(result);
        logger.info("getIssueInfo: success!");
        return issueInfo;
    }

    @GetMapping("/getRiskAnalysisInfo")
    public JSONArray getRiskAnalysisInfo() {
        String result = externalService.getRiskAnalysisInfo();
        JSONArray analysisInfo = JSONArray.parseArray(result);
        logger.info("getRiskAnalysisInfo: success!");
        return analysisInfo;
    }

}
