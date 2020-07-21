package com.nott.postgis.code.mapper;

import com.nott.postgis.code.domain.Gis;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 16:29
 * @Modified By:
 **/
@Service
public class GisMapperService {

    @Autowired
    private SqlSession sqlSession;

    public Long addGis(Gis gis) {
        GisMapper mapper = sqlSession.getMapper(GisMapper.class);
        mapper.addGis(gis);
        return gis.getId();
    }

    public void updateGis(Gis gis) {
        GisMapper mapper = sqlSession.getMapper(GisMapper.class);
        mapper.updateGis(gis);
    }

}
