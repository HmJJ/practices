package com.nott.postgis.code.controller;

import com.nott.postgis.code.domain.Gis;
import com.nott.postgis.code.mapper.GisMapperQueryService;
import com.nott.postgis.code.service.GisService;
import com.nott.postgis.code.vo.GisQuery;
import com.nott.postgis.code.vo.GisRecord;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/21 16:32
 * @Modified By:
 **/
@RestController
@RequestMapping("/gis")
public class GisController {

    public static final Logger logger = LoggerFactory.getLogger(GisController.class);

    @Autowired
    private GisService gisService;
    @Autowired
    private GisMapperQueryService mapperQueryService;

    @PostMapping("/create")
    public String create(@RequestBody Gis gis) {
        try {
            Long id = gisService.create(gis);
            return "success: " + id;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "failed: " + e.getMessage();
        }
    }

    @PostMapping("/searchAround")
    public String searchAround(@RequestBody GisQuery query) {
        try {
            List<GisRecord> records = gisService.searchAround(query);
            return "success: " + records.toString();
        } catch (ServiceException e2) {
            return "failed: " + e2.getMessage();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "failed: " + e.getMessage();
        }
    }

    @PostMapping("/searchNearest")
    public String searchNearest(@RequestBody GisQuery query) {
        try {
            List<GisRecord> records = gisService.searchNearest(query);
            return "success: " + records.toString();
        } catch (ServiceException e2) {
            return "failed: " + e2.getMessage();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "failed: " + e.getMessage();
        }
    }

}
