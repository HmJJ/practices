package com.nott.reflect.code.service;

import com.nott.reflect.code.factory.PermissionQueryFactory;
import com.nott.reflect.code.vo.RiskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/16 16:17
 * @Modified By:
 **/
@Service
public class RiskService extends PermissionQueryFactory<RiskQuery> {

    public static final Logger logger = LoggerFactory.getLogger(RiskService.class);

    public void getPermissionQuery() {
        RiskQuery query = new RiskQuery();
        query.setGroupPartyId(3L);
        query = buildPermissionQuery(query);
        logger.info("groupPartyId: {}, groupIdList: {}", query.getGroupPartyId(), query.getGroupIdList());
    }

}
