package com.rate.calculator.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.rate.calculator.model.Rate;

public class RateFieldSetMapper implements FieldSetMapper<Rate> {

	
	/**
	 * private Date date;
	private String product;
	private String indexName;
	private int cdscOption;
	private String band;
	private double capRate;
	private int mnCapRtCDSC;
	private int mnCapRtPCDSC;
	private int contractYr;
	private String mvaInd;
	 */
	@Override
	public Rate mapFieldSet(FieldSet fieldSet) throws BindException {
		Rate rate = new Rate();
		rate.setBand(fieldSet.readString("band"));
		rate.setCapRate(fieldSet.readDouble("capRate"));
		rate.setCdscOption(fieldSet.readInt("cdscOption"));
		rate.setContractYr(fieldSet.readInt("contractYr"));
		rate.setIndexName(fieldSet.readString("indexName"));
		rate.setMnCapRtCDSC(fieldSet.readInt("mnCapRtCDSC"));
		rate.setMnCapRtPCDSC(fieldSet.readInt("mnCapRtPCDSC"));
		rate.setMvaInd(fieldSet.readString("mvaInd"));
		rate.setProduct(fieldSet.readString("product"));
		rate.setEffDate(fieldSet.readString("effDate"));
		return rate;
	}
	
	@SuppressWarnings("unused")
	private Date formatDate(String date) {
		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
