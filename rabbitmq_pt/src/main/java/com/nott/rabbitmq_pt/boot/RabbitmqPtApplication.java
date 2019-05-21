package com.nott.rabbitmq_pt.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqPtApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqPtApplication.class, args);
		System.out.println("success running boot");
	}

}
