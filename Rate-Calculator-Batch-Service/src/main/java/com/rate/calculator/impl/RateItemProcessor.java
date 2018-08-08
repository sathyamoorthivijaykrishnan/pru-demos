package com.rate.calculator.impl;

import org.springframework.batch.item.ItemProcessor;

import com.rate.calculator.model.Rate;

public class RateItemProcessor implements ItemProcessor<Rate, Rate> {

	@Override
	public Rate process(Rate item) throws Exception {
		return item;
	}

}
