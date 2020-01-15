package com.nott.poi.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoiApplication.class, args);
		System.out.println("success running application !");
	}

}
