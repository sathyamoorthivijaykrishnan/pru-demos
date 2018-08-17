package com.rate.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.rate.calculator.service.RateDataService;
import com.rate.calculator.service.RateLoadService;

@EnableEurekaClient
@SpringBootApplication
public class RateCalculatorApplication {

	
	private String RATE_CAL_SERVICE = "http://RATE-CALCULATOR-SERVICE";

	public static void main(String[] args) {
		
		/*System.setProperty("spring.config.name", "application");
*/
		SpringApplication.run(RateCalculatorApplication.class, args);
	}
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	RateDataService rateDataService() {
		return new RateDataService(RATE_CAL_SERVICE);
	}
	@Bean
	RateLoadService rateLoadService() {
		return new RateLoadService(RATE_CAL_SERVICE);
	}
	
}
