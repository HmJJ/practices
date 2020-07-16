package com.nott.reflect.code.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/7/16 16:25
 * @Modified By:
 **/
public class PermissionQueryFactory<T> {

    public static final Logger logger = LoggerFactory.getLogger(PermissionQueryFactory.class);

    public T buildPermissionQuery(T query) {
        try {
            String className = query.getClass().getSimpleName();
            logger.info("className: {}", className);
            className = className.substring(0, className.length()-5);
            logger.info("className: {}", className);
            Method method = query.getClass().getMethod("getGroupPartyId", null);
            Long groupPartyId = (Long) method.invoke(query, null);
            List<Long> groupIdList = getGroupIdList(groupPartyId, className);
            Field field = query.getClass().getDeclaredField("groupIdList");
            field.setAccessible(true);
            field.set(query, groupIdList);
        } catch (InvocationTargetException e) {
            logger.info(e.getCause().getMessage());
        } catch (NoSuchMethodException e1) {
            logger.info(e1.getCause().getMessage());
        } catch (NoSuchFieldException e2) {
            logger.info(e2.getCause().getMessage());
        } catch (IllegalAccessException e3) {
            logger.info(e3.getCause().getMessage());
        }
        return query;
    }

    private List<Long> getGroupIdList(Long groupPartyId, String className) {
        List<Long> groupIdList = new ArrayList<>();
        if (groupPartyId == null) {
            groupIdList.add(1L);
        } else {
            groupIdList.add(2L);
        }
        return groupIdList;
    }

}
