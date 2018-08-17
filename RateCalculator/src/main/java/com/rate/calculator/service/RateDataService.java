package com.rate.calculator.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rate.calculator.model.Rate;

@Service
public class RateDataService {
	
	public RateDataService(String serviceName) {
		this.serviceName = serviceName;
	}

	protected String serviceName;
	
	@Autowired
	protected RestTemplate restTemplate;
	
	public List<Rate> getRates(){
		Rate rates[];
		rates= restTemplate.getForObject(serviceName+"/getRates", Rate[].class);
		for(Rate rat : rates) {
		System.out.println("*******rates*******"+ rat);
		}
		if (rates == null || rates.length == 0)
			return null;
		else
			return Arrays.asList(rates);
	}

}
