package com.nott.poi.code.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/20 10:59
 * @Modified By:
 **/
@Entity
@Table(name = "prd_product")
public class Product {

    @Id
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String productCode;

    @Getter
    @Setter
    private String productName;

    @Getter
    @Setter
    private String categoryName;

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private String unit;

}
