package com.rate.calculator.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.rate.calculator.model.Rate;

public class RateLoadService {

protected String serviceName;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	public void upload(){
		restTemplate.getForObject("http://localhost:9094/upload", Rate[].class);
		
	}


	
}
