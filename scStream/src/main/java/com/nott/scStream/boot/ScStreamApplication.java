package com.nott.scStream.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.nott"})
@MapperScan(basePackages = {"com.nott"})
@EnableJpaRepositories(basePackages = {"com.nott"})
@SpringBootApplication(scanBasePackages = {"com.nott.*"})
@EnableConfigurationProperties({LiquibaseProperties.class})
public class ScStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScStreamApplication.class, args);
	}

}
