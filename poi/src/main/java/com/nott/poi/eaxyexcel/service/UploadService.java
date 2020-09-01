/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/1 18:00
 * @Modified By:
 */
package com.nott.poi.eaxyexcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.nott.poi.code.service.ProductService;
import com.nott.poi.code.vo.ProductForm;
import com.nott.poi.code.vo.UploadVo;
import com.nott.poi.eaxyexcel.listener.ProductExcelListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service(value = "easyUploadService")
public class UploadService {

    public static final Logger log = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private ProductService productService;

    public void uploadFormProduct(UploadVo vo) {
        for (MultipartFile file : vo.getFiles()) {

        }
    }

    private void read(MultipartFile file) {
        ExcelReader excelReader = null;
        try {
            EasyExcel.read(file.getInputStream(), ProductForm.class, new ProductExcelListener(productService)).sheet().doRead();
            excelReader = EasyExcel.read(file.getInputStream(), ProductForm.class, null).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } catch (IOException e) {
            log.info(e.getCause().getMessage());
        } finally {
            if (excelReader != null) {
                excelReader.finish();
            }
        }
    }

}
