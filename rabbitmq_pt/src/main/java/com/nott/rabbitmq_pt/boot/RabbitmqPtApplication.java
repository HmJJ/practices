package com.nott.rabbitmq_pt.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.nott.rabbitmq_pt"})
@SpringBootApplication
public class RabbitmqPtApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqPtApplication.class, args);
		System.out.println("success running boot");
	}

}
