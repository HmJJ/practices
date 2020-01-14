package com.nott.rabbitmq_pt.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ComponentScan({"com.nott.rabbitmq_pt"})
@SpringBootApplication
public class RabbitmqPtApplication {

	private static Logger logger = LoggerFactory.getLogger(RabbitmqPtApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqPtApplication.class, args);

		logger.info("success running boot");
	}

}
