package com.nott.poi.code.mapper;

import com.nott.poi.code.domain.Product;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/20 11:03
 * @Modified By:
 **/
@Service
public class ExcelMapperQueryService {

    private static final Logger log = LoggerFactory.getLogger(ExcelMapperQueryService.class);

    @Autowired
    private SqlSession sqlSession;

    public List<Product> findProductList() {
        ExcelQueryMapper mapper = sqlSession.getMapper(ExcelQueryMapper.class);
        return mapper.findProductList();
    }

}
