package com.nott.scStream.code.config;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/9/18 13:41
 * @Modified By:
 **/
@Configuration
public class LiquibaseConfiguration {

    private static final Logger log = LoggerFactory.getLogger(LiquibaseConfiguration.class);

    public LiquibaseConfiguration() {}

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setDatabaseChangeLogTable("databasechangelog");
        liquibase.setDatabaseChangeLogLockTable("databasechangeloglock");
        liquibase.setShouldRun(liquibaseProperties.isEnabled());
        this.log.info("Configuring Liquibase");
        return liquibase;
    }

}
