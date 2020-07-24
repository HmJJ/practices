package com.nott.postgis.code.mapper;

import com.nott.postgis.code.vo.GisQuery;
import com.nott.postgis.code.vo.GisRecord;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 17:06
 * @Modified By:
 **/
public interface GisQueryMapper {

    List<GisRecord> findGisWithinList(GisQuery query);

    List<GisRecord> findNearestList(GisQuery query);
}
