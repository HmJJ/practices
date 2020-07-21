package com.nott.postgis.code.service;

import com.nott.postgis.code.domain.Gis;
import com.nott.postgis.code.mapper.GisMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Long create(Gis gis) {
        String geoStr = "POINT(" + gis.getLongitude().toString() + " " + gis.getLatitude().toString() + ")";
        gis.setGeoStr(geoStr);
        Long id = mapperService.addGis(gis);
        return id;
    }

    public void modify(Gis gis) {
        mapperService.updateGis(gis);
    }

}
