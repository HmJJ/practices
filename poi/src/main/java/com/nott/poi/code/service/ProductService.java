package com.nott.poi.code.service;

import com.nott.poi.code.domain.Product;
import com.nott.poi.code.mapper.ExcelMapperQueryService;
import com.nott.poi.excel.factory.ExcelFactory;
import com.nott.poi.excel.factory.ExcelFactoryConcrete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/20 11:08
 * @Modified By:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ExcelMapperQueryService mapperQueryService;
    @Autowired
    private ExcelFactoryConcrete<Product> excelFactoryConcrete;

    public byte[] getInfo() {
        List<Product> products = mapperQueryService.findProductList();
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("没有找到值！");
        }
        ExcelFactory excelFactory = excelFactoryConcrete.getService("xlsx");
        byte[] data = null;
        try {
            data = excelFactory.write(products);
        } catch (IOException e) {
            log.error(e.getCause().getMessage());
        }
        return data;
    }

}
