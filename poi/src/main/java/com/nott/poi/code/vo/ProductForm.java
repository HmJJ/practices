/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/1 18:09
 * @Modified By:
 */
package com.nott.poi.code.vo;

import lombok.Data;

@Data
public class ProductForm {

    protected String productCode;
    protected String productName;
    protected String categoryName;
    protected Integer quantity;
    protected String unit;

}
