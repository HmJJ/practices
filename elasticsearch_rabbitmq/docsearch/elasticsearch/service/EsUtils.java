package com.wayonsys.ecm.docsearch.elasticsearch.service;

import com.wayonsys.common.contract.ResultVo;
import com.wayonsys.common.entity.DomainEntity;
import com.wayonsys.ecm.docsearch.elasticsearch.vo.ElasticForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/10/15 15:49
 * @Modified By:
 **/
@Service
public class EsUtils {

    public static final Logger log = LoggerFactory.getLogger(EsUtils.class);

    @Autowired
    private ElasticService elasticService;

    public ResultVo save(ElasticForm form) {
        ResultVo resultVo = new ResultVo();
        boolean flag = false;
        if (form.getMultipartFile() == null) {
            flag = elasticService.saveIndex(form);
        } else {
            MultipartFile file = form.getMultipartFile();
            String contentType = file.getContentType();
            switch (contentType) {
                case "application/msword":
                case "application/pdf":
                case "application/x-ppt":
                    Map<String, Object> map = form.getMap();
                    flag = elasticService.saveIndex(file, map);
                    break;
                default:
                    break;
            }
        }
        resultVo.setData(flag);
        return resultVo;
    }

}
