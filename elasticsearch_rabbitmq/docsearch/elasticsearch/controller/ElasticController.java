package com.wayonsys.ecm.docsearch.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.wayonsys.common.contract.BaseVo;
import com.wayonsys.common.contract.ResultVo;
import com.wayonsys.common.entity.DomainEntity;
import com.wayonsys.ecm.docsearch.elasticsearch.service.ElasticService;
import com.wayonsys.ecm.docsearch.elasticsearch.service.EsUtils;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticForm;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticQuery;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticRecord;
import com.wayonsys.ecm.docsearch.rabbitmq.service.RabbitService;
import com.wayonsys.ecm.utils.FileUtils;
import com.wayonsys.framework.security.SecurityUtils;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/25 17:25
 * @Modified By:
 **/
@RequestMapping("/api/elastic")
@RestController
public class ElasticController {

    private static final Logger log = LoggerFactory.getLogger(ElasticController.class);

    @Autowired
    private EsUtils esUtils;
    @Autowired
    private ElasticService elasticService;
    @Autowired
    private RabbitService rabbitService;

    @RequestMapping("save")
    public String save(@RequestBody ElasticForm form) {
        esUtils.save(form);
        return "ok";
    }

    @RequestMapping("searchDoc")
    public ResultVo searchDoc(@RequestBody ElasticQuery query) {
        ResultVo resultVo = new ResultVo();
        try {
            Long tenantId = SecurityUtils.getMyAccountInfo().getTenantId();
            log.info("当前用户的租户id：{}", tenantId);
            query.setTenantId(String.valueOf(tenantId));
            String[] matchFields = {"name"};
            query.setMatchFields(matchFields);
            query.setIncludeFields(new String[]{"id", "tenantId", "name", "store", "creator", "suffix", "createTime", "highlight"});
            query.setExcludeFields(matchFields);
            List<ElasticRecord> course = elasticService.searchIndex(query);
            resultVo.setData(course);
        } catch (Exception e) {
//            log.info(e.getCause().getMessage());
            e.printStackTrace();
        }
        return resultVo;
    }

    @RequestMapping("searchContent")
    public ResultVo searchContent(@RequestBody ElasticQuery query) {
        ResultVo resultVo = new ResultVo();
        try {
            Long tenantId = SecurityUtils.getMyAccountInfo().getTenantId();
            log.info("tenantId: {}", tenantId);
            query.setTenantId(String.valueOf(tenantId));
            String[] matchFields = {"attachment.content"};
            query.setMatchFields(matchFields);
            query.setIncludeFields(new String[]{"id", "tenantId", "name", "store", "createTime", "creator", "suffix", "highlight"});
            query.setExcludeFields(matchFields);
            List<ElasticRecord> course = elasticService.searchIndex(query);
            resultVo.setData(course);
        } catch (Exception e) {
            log.info(e.getCause().getMessage());
        }
        return resultVo;
    }

    @RequestMapping("searchContentWithContent")
    public ResultVo searchContentWithContent(@RequestBody ElasticQuery query) {
        ResultVo resultVo = new ResultVo();
        try {
            Long tenantId = SecurityUtils.getMyAccountInfo().getTenantId();
            query.setTenantId(String.valueOf(tenantId));
            String[] matchFields = {"store"};
            query.setMatchFields(matchFields);
            query.setIncludeFields(new String[]{"id", "tenantId", "name", "store", "createTime", "creator", "suffix", "attachment.content", "highlight"});
            query.setExcludeFields(new String[]{});
            List<ElasticRecord> course = elasticService.searchIndex(query);
            resultVo.setData(course);
        } catch (Exception e) {
            log.info(e.getCause().getMessage());
        }
        return resultVo;
    }

    @RequestMapping("deleteDoc")
    public String deleteDoc(@RequestBody ElasticQuery query) {
        try {
            elasticService.deleteIndex(query);
        } catch (Exception e) {
            log.info(e.getCause().getMessage());
        }
        return "ok";
    }

    @RequestMapping("saveDoc")
    public String saveDoc(@RequestBody ElasticQuery query) {
        rabbitService.send(query.getId());
//        elasticService.saveIndex(form);
        return "ok";
    }

    @RequestMapping(value = "saveDocOrPdfOrPpt", method = RequestMethod.GET)
    public String saveDocOrPdfOrPpt(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename().toLowerCase();
        String[] nameAndType = fileName.split("\\.");
        switch (nameAndType[1]) {
            case "pdf":
            case "doc":
            case "docx":
            case "ppt":
            case "ppts":
                elasticService.saveIndex(file, new HashMap<>());
                break;
            default:
                break;
        }
        return "ok";
    }
}
