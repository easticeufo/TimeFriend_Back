package com.madongfang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StatementPK.class)
public class CashFlowStatement {

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getJyhdxjlrxj() {
		return jyhdxjlrxj;
	}

	public void setJyhdxjlrxj(Integer jyhdxjlrxj) {
		this.jyhdxjlrxj = jyhdxjlrxj;
	}

	public Integer getJyhdxjlcxj() {
		return jyhdxjlcxj;
	}

	public void setJyhdxjlcxj(Integer jyhdxjlcxj) {
		this.jyhdxjlcxj = jyhdxjlcxj;
	}

	public Integer getJyhdcsdxjllje() {
		return jyhdcsdxjllje;
	}

	public void setJyhdcsdxjllje(Integer jyhdcsdxjllje) {
		this.jyhdcsdxjllje = jyhdcsdxjllje;
	}

	public Integer getTzhdxjlrxj() {
		return tzhdxjlrxj;
	}

	public void setTzhdxjlrxj(Integer tzhdxjlrxj) {
		this.tzhdxjlrxj = tzhdxjlrxj;
	}

	public Integer getTzhdxjlcxj() {
		return tzhdxjlcxj;
	}

	public void setTzhdxjlcxj(Integer tzhdxjlcxj) {
		this.tzhdxjlcxj = tzhdxjlcxj;
	}

	public Integer getTzhdcsdxjllje() {
		return tzhdcsdxjllje;
	}

	public void setTzhdcsdxjllje(Integer tzhdcsdxjllje) {
		this.tzhdcsdxjllje = tzhdcsdxjllje;
	}

	public Integer getCzhdxjlrxj() {
		return czhdxjlrxj;
	}

	public void setCzhdxjlrxj(Integer czhdxjlrxj) {
		this.czhdxjlrxj = czhdxjlrxj;
	}

	public Integer getCzhdxjlcxj() {
		return czhdxjlcxj;
	}

	public void setCzhdxjlcxj(Integer czhdxjlcxj) {
		this.czhdxjlcxj = czhdxjlcxj;
	}

	public Integer getCzhdcsdxjllje() {
		return czhdcsdxjllje;
	}

	public void setCzhdcsdxjllje(Integer czhdcsdxjllje) {
		this.czhdcsdxjllje = czhdcsdxjllje;
	}

	public Integer getQmxjjxjdjwye() {
		return qmxjjxjdjwye;
	}

	public void setQmxjjxjdjwye(Integer qmxjjxjdjwye) {
		this.qmxjjxjdjwye = qmxjjxjdjwye;
	}

	@Id
	@Column(length=6)
	private String stockCode;
	
	@Id
	private Integer year;
	
	private Integer jyhdxjlrxj;
	
	private Integer jyhdxjlcxj;
	
	private Integer jyhdcsdxjllje;
	
	private Integer tzhdxjlrxj;
	
	private Integer tzhdxjlcxj;
	
	private Integer tzhdcsdxjllje;
	
	private Integer czhdxjlrxj;
	
	private Integer czhdxjlcxj;
	
	private Integer czhdcsdxjllje;
	
	private Integer qmxjjxjdjwye;
}
