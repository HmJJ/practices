package com.nott.redis_pt.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.nott"})
@MapperScan(basePackages = {"com.nott.redis_pt.*.mapper"})
@EntityScan(basePackages = {"com.nott"})
@EnableJpaRepositories(basePackages = {"com.nott"})
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
