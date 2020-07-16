package com.nott.reflect.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.nott.*")
@SpringBootApplication
public class ReflectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReflectApplication.class, args);
		System.out.println("Success running application !");
	}

}
