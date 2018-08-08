package com.rate.calculator.impl;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.rate.calculator.dao.RateRepository;
import com.rate.calculator.model.Rate;

public class RateJobListener implements JobExecutionListener {

	@Autowired
	protected RateRepository rateRepository;
	
	@Override
	public void afterJob(JobExecution jobExecution) {

		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
			System.out.println("Rates found with findAll():");
			System.out.println("-------------------------------");
			for (Rate rate : rateRepository.findAll()) {
				System.out.println(rate);
			}
			System.out.println();
		}
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		System.out.println("*********************************BATCH JOB STARTED*********************************");
	}

}
