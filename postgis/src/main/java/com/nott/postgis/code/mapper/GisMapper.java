package com.nott.postgis.code.mapper;

import com.nott.postgis.code.domain.Gis;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 16:20
 * @Modified By:
 **/
public interface GisMapper {

    Long addGis(Gis gis);
    void updateGis(Gis gis);

}
