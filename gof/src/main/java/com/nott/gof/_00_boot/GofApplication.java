package com.nott.gof._00_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ComponentScan({"com.nott.gof"})
@SpringBootApplication
public class GofApplication {

	public static void main(String[] args) {
//		SpringApplication.run(GofApplication.class, args);


		String dateStr = "2019-10-29T23:48:19";
		dateStr = dateStr.replace("T", " ");
		DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse(dateStr, d);
		System.out.println(ldt);

	}

}
