package com.nott.io.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.sql.DataSourceDefinition;

@ComponentScan(value = "com.nott")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoApplication.class, args);
	}

	@RequestMapping("/")
	public String hello() {
		return "index";
	}

}
