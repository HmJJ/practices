/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/1 18:01
 * @Modified By:
 */
package com.nott.poi.code.service;

import com.nott.poi.eaxyexcel.service.EasyExcelService;
import com.nott.poi.poiexcel.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    @Qualifier("poiUploadService")
    private UploadService poiUploadService;

    @Autowired
    private EasyExcelService easyEasyExcelService;

}
