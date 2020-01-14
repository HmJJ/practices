package com.nott.sort.service;

import com.nott.sort.common.Sort;
import com.nott.sort.common.SortFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/1/14 15:32
 * @Modified By:
 **/
@Service
public class SortService {

    private static final Logger log = LoggerFactory.getLogger(SortService.class);

    @Autowired
    private SortFactory sortFactory;

    public String sort(Map<String, Object> map) {

        String type = (String) map.get("type");
        if (StringUtils.isEmpty(type)) {
            return "请选择一种排序方式：[bubble, selection, insertion]";
        }
        int arr[] = {9, 2, 0, 3, 7, 4, 6, 5};
        Sort sort = sortFactory.getService(type);
        if (sort == null) {
            return "请输入正确的排序方式！";
        }
        try {
            sort.sort(arr);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "ok";
    }
}
