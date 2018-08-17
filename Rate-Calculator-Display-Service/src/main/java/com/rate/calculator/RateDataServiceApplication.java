package com.rate.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.rate.calculator")
public class RateDataServiceApplication {

	public static void main(String[] args) {
		
		System.setProperty("spring.config.name", "application");

		SpringApplication.run(RateDataServiceApplication.class, args);
	}
}
