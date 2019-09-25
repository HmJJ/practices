package com.nott.scStream.code.elasticsearch.service;

import com.nott.scStream.code.demo.domain.Base;
import io.searchbox.client.JestClient;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/17 10:46
 * @Modified By:
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ESService {

    private static final Logger log = LoggerFactory.getLogger(ESService.class);
    private static final String CURRENT_MODEL_PACKAGE_NAME = "com.nott.scStream.code.elasticsearch.service";
    private static final String DEFAULT_INDEX = "nott";

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private JestClient jestClient;


    /**
     * 根据Base子类数据产生一个Index数据
     * @param source
     * @return
     */
    private Index indexGenerator(Base source) {
        Index index = null;
        if (StringUtils.isEmpty(source.getEsIndex())) {
            source.setEsIndex(DEFAULT_INDEX);
        }
        try {
            String[] tempArr = source.getClass().getName().split("\\.");
            String name = tempArr[tempArr.length - 1];
            source.setEsType(name);
            index = new Index.Builder(source).index(source.getEsIndex()).type(source.getEsType()).build();
        } catch (Exception e) {
            log.info(e.getCause().getMessage());
        }
        return index;
    }

    /**
     * 批量存储接口
     * @param bases
     * @return
     */
    public boolean batchSave(List<? extends Base> bases) {
        List<Index> indexList = new ArrayList<>();
        bases.forEach(el -> indexList.add(indexGenerator(el)));
        boolean flag = false;
        try {
            for (Index index : indexList) {
                jestClient.execute(index);
            }
        } catch (IOException e) {
            log.info(e.getCause().getMessage());
        }
        return flag;
    }

    /**
     * 保存单个索引文档数据
     * @param source
     * @return
     */
    public boolean singleSave(Base source) {
        Index single;
        boolean flag = false;
        try {
            single = indexGenerator(source);
            jestClient.execute(single);
            flag = true;
        } catch (IOException e) {
            log.info(e.getCause().getMessage());
        }
        return flag;
    }

    /**
     * 私有的基础查询
     * @param searchStr
     * @return
     */
    public String baseQuery(String searchStr, String index, String type) {
        try {
            Search search = new Search.Builder(searchStr).addIndex(index).addType(type).build();
            SearchResult result = jestClient.execute(search);
            return  result.getJsonString();
        } catch (IOException e) {
            log.info(e.getCause().getMessage());
        }
        return null;
    }

    /**
     * 基础删除接口
     * @param deleteStr
     * @param index
     * @param type
     * @return
     */
    public boolean deleteBase(String deleteStr, String index, String type) {
        boolean flag = false;
        try {
            Delete delete = new Delete.Builder(deleteStr).index(index).type(type).build();
            jestClient.execute(delete);
            flag = true;
        } catch (IOException e) {
            log.info(e.getCause().getMessage());
        }
        return flag;
    }

}
