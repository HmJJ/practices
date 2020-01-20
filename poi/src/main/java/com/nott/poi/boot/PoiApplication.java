package com.nott.poi.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(value = "com.nott")
@EntityScan(basePackages = {"com.nott"})
@EnableJpaRepositories(basePackages = {"com.nott"})
@MapperScan({"com.nott.**.mapper"})
@SpringBootApplication
public class PoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
		System.out.println("success running application !");
	}

}
