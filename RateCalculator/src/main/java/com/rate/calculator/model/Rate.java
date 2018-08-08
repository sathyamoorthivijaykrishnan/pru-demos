package com.rate.calculator.model;

public class Rate {

	// effDate product indexName cdscOption band capRate mnCapRtCDSC mnCapRtPCDSC
	// contractYr mvaInd
	@Override
	public String toString() {
		return "Rate [effDate=" + effDate + ", product=" + product + ", indexName=" + indexName + ", cdscOption="
				+ cdscOption + ", band=" + band + ", capRate=" + capRate + ", mnCapRtCDSC=" + mnCapRtCDSC
				+ ", mnCapRtPCDSC=" + mnCapRtPCDSC + ", contractYr=" + contractYr + ", mvaInd=" + mvaInd + "]";
	}

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

	public String getEffDate() {
		return effDate;
	}

	public void setEffDate(String effDate) {
		this.effDate = effDate;
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
