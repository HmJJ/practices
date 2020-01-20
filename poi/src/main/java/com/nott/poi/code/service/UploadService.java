package com.nott.poi.code.service;

import com.alibaba.fastjson.JSONArray;
import com.nott.poi.excel.factory.ExcelFactory;
import com.nott.poi.excel.factory.ExcelFactoryConcrete;
import com.nott.poi.code.vo.UploadVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/15 10:42
 * @Modified By:
 **/
@Service
public class UploadService {

    private static final Logger log = LoggerFactory.getLogger(UploadService.class);
    @Autowired
    private ExcelFactoryConcrete excelFactoryConcrete;

    public void upload(UploadVo vo) {
        validate(vo);
        Long orderId = vo.getOrderId();
        log.info("orderId: {}", orderId);
        for (MultipartFile file : vo.getFiles()) {
            JSONArray array = parseExcel(file);
            if (array == null)
                continue;
            Iterator iterator = array.iterator();
            if (iterator.hasNext()) {
                // 业务
            }
        }
    }

    private void validate(UploadVo vo) {
        if (vo.getOrderId() == null) {
            log.error("orderId为必传项!");
        }
        if (vo.getFiles() == null || vo.getFiles().isEmpty()) {
            log.error("请选择商品明细的excel!");
        }
    }

    private JSONArray parseExcel(MultipartFile file) {
        String[] params = file.getOriginalFilename().split("\\.");
        ExcelFactory xmlFactory = excelFactoryConcrete.getService(params[1]);
        JSONArray array = null;
        try {
            array = xmlFactory.readFile(file.getInputStream());
        } catch (IOException e) {
            log.error(e.getCause().getMessage());
            log.error("读取文件失败！");
        }
        return array;
    }

}
