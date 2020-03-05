package com.nott.poi.code.controller;

import com.nott.poi.code.service.ProductService;
import com.nott.poi.code.service.UploadService;
import com.nott.poi.code.vo.UploadVo;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/20 10:58
 * @Modified By:
 **/
@Controller
@RequestMapping("excel")
public class ExcelController {

    private static Logger log = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/getExcel")
    public void getExcel(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        byte[] date = productService.getInfo();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fileName = dtf.format(now) + "_" + now.getNano() + ".xlsx";
        response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(date);
        try {
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            log.error(e.getCause().getMessage());
        }
    }

    @PostMapping(value = "/parseExcel")
    public void parseExcel(@RequestParam List<MultipartFile> files) {
        UploadVo vo = new UploadVo();
        vo.setFiles(files);
        uploadService.upload(vo);
    }

}
