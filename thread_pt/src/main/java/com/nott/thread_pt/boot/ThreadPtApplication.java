package com.nott.thread_pt.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.nott.thread_pt"})
@SpringBootApplication
public class ThreadPtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadPtApplication.class, args);
	}

}
