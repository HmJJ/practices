package com.nott.gof.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.nott.gof"})
@SpringBootApplication
public class GofApplication {

	public static void main(String[] args) {
		SpringApplication.run(GofApplication.class, args);
	}

}
