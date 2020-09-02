/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/1 18:19
 * @Modified By:
 */
package com.nott.poi.eaxyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.nott.poi.code.service.ProductService;
import com.nott.poi.code.vo.ProductForm;
import com.nott.poi.eaxyexcel.vo.TemplateVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductExcelListener extends AnalysisEventListener<ProductForm> {

    public static final Logger log = LoggerFactory.getLogger(ProductExcelListener.class);

    public static final int BATCH_COUNT = 3000;
    List<ProductForm> list = new ArrayList<>();

    private ProductService productService;
    public ProductExcelListener() {
        productService = new ProductService();
    }
    public ProductExcelListener(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void invoke(ProductForm data, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        //TODO
        log.info("存储数据库成功！");
    }
}
