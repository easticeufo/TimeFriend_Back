package com.madongfang.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class StockApi {

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Double getMlv() {
		return mlv;
	}

	public void setMlv(Double mlv) {
		this.mlv = mlv;
	}

	public Double getHbzjzb() {
		return hbzjzb;
	}

	public void setHbzjzb(Double hbzjzb) {
		this.hbzjzb = hbzjzb;
	}

	public Double getJlrhjl() {
		return jlrhjl;
	}

	public void setJlrhjl(Double jlrhjl) {
		this.jlrhjl = jlrhjl;
	}

	public Double getZzczzl() {
		return zzczzl;
	}

	public void setZzczzl(Double zzczzl) {
		this.zzczzl = zzczzl;
	}

	private String type;
	
	private String name;
	
	private String stockCode;
	
	private Double mlv;
	
	private Double hbzjzb;
	
	private Double jlrhjl;
	
	private Double zzczzl;
}
