package com.nott.scStream.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.nott.scStream"})
@SpringBootApplication
public class ScStreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScStreamApplication.class, args);
	}

}
