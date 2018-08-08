package com.rate.calculator.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.rate.calculator.dao.RateRepository;
import com.rate.calculator.model.Rate;

public class RateItemWriter implements ItemWriter<Rate> {
	
	@Autowired
	protected RateRepository rateRepository;
	
	@Override
	public void write(List<? extends Rate> items) throws Exception {
		for (Rate rate : items) {			
			rate.setUpdatedOn(getCurrentDate());
			rateRepository.save(rate);
		}
	}
	
	private String getCurrentDate(){
		return new SimpleDateFormat("MM/dd/yyyy").format(new Date());
	}
}
