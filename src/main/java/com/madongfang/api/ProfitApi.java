package com.madongfang.api;

import java.util.List;

public class ProfitApi {

	public List<String> getYears() {
		return years;
	}

	public void setYears(List<String> years) {
		this.years = years;
	}

	public List<Integer> getNetProfits() {
		return netProfits;
	}

	public void setNetProfits(List<Integer> netProfits) {
		this.netProfits = netProfits;
	}

	public List<Integer> getOperatingProfits() {
		return operatingProfits;
	}

	public void setOperatingProfits(List<Integer> operatingProfits) {
		this.operatingProfits = operatingProfits;
	}

	public List<String> getNetProfitRates() {
		return netProfitRates;
	}

	public void setNetProfitRates(List<String> netProfitRates) {
		this.netProfitRates = netProfitRates;
	}

	public List<String> getOperatingProfitRates() {
		return operatingProfitRates;
	}

	public void setOperatingProfitRates(List<String> operatingProfitRates) {
		this.operatingProfitRates = operatingProfitRates;
	}

	private List<String> years;
	
	private List<Integer> netProfits;
	
	private List<Integer> operatingProfits;
	
	private List<String> netProfitRates;
	
	private List<String> operatingProfitRates;
}
