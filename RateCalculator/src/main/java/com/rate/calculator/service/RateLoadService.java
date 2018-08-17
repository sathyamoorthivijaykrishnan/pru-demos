package com.rate.calculator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rate.calculator.model.Rate;

@Service
public class RateLoadService {

protected String serviceName;
	
private List<Rate> rates;
	@Autowired
	protected RestTemplate restTemplate;
	
	public String loadRates(List<String[]> allRows) {
		rates = new ArrayList<Rate>();
		for(String[] row :allRows) {
			Rate rate = new Rate();
			rate.setEffDate(row[0]);
			rate.setProduct(row[1]);
			rate.setIndexName(row[2]);
			rate.setCdscOption(Integer.parseInt(row[3]));
			rate.setBand(row[4]);
			rate.setCapRate(Double.parseDouble(row[5]));
			rate.setMnCapRtCDSC(Integer.parseInt(row[6]));
			rate.setMnCapRtPCDSC(Integer.parseInt(row[7]));
			rate.setContractYr(Integer.parseInt(row[8]));			
			rate.setMvaInd(row[9]);
			rates.add(rate);
		}
		  
		return	restTemplate.postForObject(serviceName+"/upload", rates, String.class);
	     
	}

	public RateLoadService(String serviceName) {
		super();
		this.serviceName = serviceName;
	}


	
}
