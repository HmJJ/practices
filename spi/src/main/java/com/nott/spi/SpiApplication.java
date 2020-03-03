package com.nott.spi;

import com.nott.spi.service.SPIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.Service;

import java.util.Iterator;

//@SpringBootApplication
public class SpiApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpiApplication.class, args);
		Iterator<SPIService> providers = Service.providers(SPIService.class);
		int sum = 0;
		while (providers.hasNext()) {
			SPIService ser = providers.next();
			sum += ser.getNum();
		}
		System.out.println("实现类返回值的总和：" + sum);
	}

}
