package com.nott.security.common.config;

import liquibase.changelog.IncludeAllFilter;

/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/8/11 18:30
 * @Modified By:
 **/
public class ChangeLogFilter implements IncludeAllFilter {
    @Override
    public boolean include(String changeLogPath) {

        boolean result = changeLogPath.endsWith(".xml");
//        result = result && changeLogPath.startsWith("liquibase");
        return result;
    }
}
