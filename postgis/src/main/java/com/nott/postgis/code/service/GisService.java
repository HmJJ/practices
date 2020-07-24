package com.nott.postgis.code.service;

import com.nott.postgis.code.domain.Gis;
import com.nott.postgis.code.mapper.GisMapperQueryService;
import com.nott.postgis.code.mapper.GisMapperService;
import com.nott.postgis.code.vo.GisQuery;
import com.nott.postgis.code.vo.GisRecord;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 16:31
 * @Modified By:
 **/
@Service
public class GisService {

    @Autowired
    private GisMapperService mapperService;
    @Autowired
    private GisMapperQueryService mapperQueryService;

    public Long create(Gis gis) {
        String geoStr = "POINT(" + gis.getLongitude().toString() + " " + gis.getLatitude().toString() + ")";
        gis.setGeoStr(geoStr);
        Long id = mapperService.addGis(gis);
        return id;
    }

    public void modify(Gis gis) {
        mapperService.updateGis(gis);
    }

    public List<GisRecord> searchAround(GisQuery query) {
        validate(query);
        List<GisRecord> records = mapperQueryService.findWithinList(query);
        return records;
    }

    public List<GisRecord> searchNearest(GisQuery query) {
        validate(query);
        List<GisRecord> records = mapperQueryService.findNearestList(query);
        return records;
    }

    private void validate(GisQuery query) {
        if (query.getLongitude() == null || query.getLatitude() == null) {
            throw new ServiceException("请传入当前的gis：{longitude，latitude}");
        }
    }

}
