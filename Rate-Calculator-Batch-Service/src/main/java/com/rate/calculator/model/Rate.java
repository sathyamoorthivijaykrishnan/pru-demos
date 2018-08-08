package com.rate.calculator.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection ="Rate")
public class Rate {

	@Override
	public String toString() {
		return "Rate [id=" + id + ", effDate=" + effDate + ", product=" + product + ", indexName=" + indexName
				+ ", cdscOption=" + cdscOption + ", band=" + band + ", capRate=" + capRate + ", mnCapRtCDSC="
				+ mnCapRtCDSC + ", mnCapRtPCDSC=" + mnCapRtPCDSC + ", contractYr=" + contractYr + ", mvaInd=" + mvaInd
				+ ", updatedOn=" + updatedOn + "]";
	}

	// effDate product indexName cdscOption band capRate mnCapRtCDSC mnCapRtPCDSC
	// contractYr mvaInd
	@Id
	private String id;	
	private String effDate;
	private String product;
	private String indexName;
	private int cdscOption;
	private String band;
	private double capRate;
	
	
	private int mnCapRtCDSC;
	private int mnCapRtPCDSC;
	private int contractYr;
	private String mvaInd;
	
	private String updatedOn;

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}


	public String getEffDate() {
		return effDate;
	}

	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public int getCdscOption() {
		return cdscOption;
	}

	public void setCdscOption(int cdscOption) {
		this.cdscOption = cdscOption;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public double getCapRate() {
		return capRate;
	}

	public void setCapRate(double capRate) {
		this.capRate = capRate;
	}

	public int getMnCapRtCDSC() {
		return mnCapRtCDSC;
	}

	public void setMnCapRtCDSC(int mnCapRtCDSC) {
		this.mnCapRtCDSC = mnCapRtCDSC;
	}

	public int getMnCapRtPCDSC() {
		return mnCapRtPCDSC;
	}

	public void setMnCapRtPCDSC(int mnCapRtPCDSC) {
		this.mnCapRtPCDSC = mnCapRtPCDSC;
	}

	public int getContractYr() {
		return contractYr;
	}

	public void setContractYr(int contractYr) {
		this.contractYr = contractYr;
	}

	public String getMvaInd() {
		return mvaInd;
	}

	public void setMvaInd(String mvaInd) {
		this.mvaInd = mvaInd;
	}

}
