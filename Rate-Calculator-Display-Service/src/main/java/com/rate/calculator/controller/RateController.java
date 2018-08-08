package com.rate.calculator.controller;
import java.io.File;
import java.text.ParseException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rate.calculator.dao.RateRepository;
import com.rate.calculator.model.Rate;

@RestController
public class RateController {
	
	@Autowired
	protected RateRepository rateRepository;
	
	@GetMapping(value = "/getRates")
	public List<Rate> getRates(){
		return rateRepository.findAll();			
	}
	
	@GetMapping(value = "/getRates/{effDate}")
	public List<Rate> getRatesForEffDate(@PathVariable("effDate") String effDate) throws ParseException{
		return rateRepository.findByEffDate(effDate);	
		
	}
	@PostMapping(value = "/upload/{file}")
	public List<Rate> uploadFile(@PathVariable("file") File file){
		return rateRepository.findAll();			
	}
	
	

}
