package com.nott.baiduAI.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = {"com.nott"})
@SpringBootApplication
public class BaiduAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaiduAiApplication.class, args);
	}

}
