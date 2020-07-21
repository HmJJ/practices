package com.nott.postgis.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nott"})
@MapperScan(basePackages = {"com.nott"})
public class PostgisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostgisApplication.class, args);
	}

}
