package com.nott.postgis.code.mapper;

import com.nott.postgis.code.vo.GisQuery;
import com.nott.postgis.code.vo.GisRecord;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 17:09
 * @Modified By:
 **/
@Service
public class GisMapperQueryService {

    @Autowired
    private SqlSession sqlSession;

    public List<GisRecord> findWithinList(GisQuery query) {
        query = beforeSearch(query);
        GisQueryMapper mapper = sqlSession.getMapper(GisQueryMapper.class);
        List<GisRecord> gisList = mapper.findGisWithinList(query);
        return gisList;
    }

    public List<GisRecord> findNearestList(GisQuery query) {
        query = beforeSearch(query);
        GisQueryMapper mapper = sqlSession.getMapper(GisQueryMapper.class);
        List<GisRecord> gisList = mapper.findNearestList(query);
        return gisList;
    }

    private GisQuery beforeSearch(GisQuery query) {
        String geoStr = "SRID=4326;POINT(" + query.getLongitude().toString() + " " + query.getLatitude().toString() + ")";
        query.setGeoStr(geoStr);
        return query;
    }
}
