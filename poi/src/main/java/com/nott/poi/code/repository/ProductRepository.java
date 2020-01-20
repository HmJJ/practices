package com.nott.poi.code.repository;

import com.nott.poi.code.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/20 11:07
 * @Modified By:
 **/
public interface ProductRepository extends JpaRepository<Product, Long> {
}
