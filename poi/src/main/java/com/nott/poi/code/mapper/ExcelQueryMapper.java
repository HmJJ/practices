package com.nott.poi.code.mapper;

import com.nott.poi.code.domain.Product;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/20 11:02
 * @Modified By:
 **/
public interface ExcelQueryMapper {

    List<Product> findProductList();

}
