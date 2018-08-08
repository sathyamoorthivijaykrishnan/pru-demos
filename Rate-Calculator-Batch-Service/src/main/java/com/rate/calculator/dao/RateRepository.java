package com.rate.calculator.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rate.calculator.model.Rate;

@Repository
public interface RateRepository extends MongoRepository<Rate, String> {
	
 List<Rate> findByProduct(String Product);
 List<Rate> findByUpdatedOn(Date date);
 
}
