package com.nott.sort.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.nott.*")
@EnableAspectJAutoProxy
@SpringBootApplication
public class SortApplication {

	public static void main(String[] args) {
		SpringApplication.run(SortApplication.class, args);
		System.out.println("Success running application !");
	}

}
