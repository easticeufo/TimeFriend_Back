package com.madongfang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StatementPK.class)
public class BalanceSheet {

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

	public Integer getHbzj() {
		return hbzj;
	}

	public void setHbzj(Integer hbzj) {
		this.hbzj = hbzj;
	}

	public Integer getYspj() {
		return yspj;
	}

	public void setYspj(Integer yspj) {
		this.yspj = yspj;
	}

	public Integer getYszk() {
		return yszk;
	}

	public void setYszk(Integer yszk) {
		this.yszk = yszk;
	}

	public Integer getYfkx() {
		return yfkx;
	}

	public void setYfkx(Integer yfkx) {
		this.yfkx = yfkx;
	}

	public Integer getQtysk() {
		return qtysk;
	}

	public void setQtysk(Integer qtysk) {
		this.qtysk = qtysk;
	}

	public Integer getCh() {
		return ch;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}

	public Integer getLdzchj() {
		return ldzchj;
	}

	public void setLdzchj(Integer ldzchj) {
		this.ldzchj = ldzchj;
	}

	public Integer getGdzc() {
		return gdzc;
	}

	public void setGdzc(Integer gdzc) {
		this.gdzc = gdzc;
	}

	public Integer getZjgc() {
		return zjgc;
	}

	public void setZjgc(Integer zjgc) {
		this.zjgc = zjgc;
	}

	public Integer getWxzc() {
		return wxzc;
	}

	public void setWxzc(Integer wxzc) {
		this.wxzc = wxzc;
	}

	public Integer getSy() {
		return sy;
	}

	public void setSy(Integer sy) {
		this.sy = sy;
	}

	public Integer getFldzchj() {
		return fldzchj;
	}

	public void setFldzchj(Integer fldzchj) {
		this.fldzchj = fldzchj;
	}

	public Integer getZczj() {
		return zczj;
	}

	public void setZczj(Integer zczj) {
		this.zczj = zczj;
	}

	public Integer getDqjk() {
		return dqjk;
	}

	public void setDqjk(Integer dqjk) {
		this.dqjk = dqjk;
	}

	public Integer getYfpj() {
		return yfpj;
	}

	public void setYfpj(Integer yfpj) {
		this.yfpj = yfpj;
	}

	public Integer getYfzk() {
		return yfzk;
	}

	public void setYfzk(Integer yfzk) {
		this.yfzk = yfzk;
	}

	public Integer getLdfzhj() {
		return ldfzhj;
	}

	public void setLdfzhj(Integer ldfzhj) {
		this.ldfzhj = ldfzhj;
	}

	public Integer getFldfzhj() {
		return fldfzhj;
	}

	public void setFldfzhj(Integer fldfzhj) {
		this.fldfzhj = fldfzhj;
	}

	public Integer getFzhj() {
		return fzhj;
	}

	public void setFzhj(Integer fzhj) {
		this.fzhj = fzhj;
	}

	public Integer getSszb() {
		return sszb;
	}

	public void setSszb(Integer sszb) {
		this.sszb = sszb;
	}

	public Integer getZbgj() {
		return zbgj;
	}

	public void setZbgj(Integer zbgj) {
		this.zbgj = zbgj;
	}

	public Integer getYygj() {
		return yygj;
	}

	public void setYygj(Integer yygj) {
		this.yygj = yygj;
	}

	public Integer getWfplr() {
		return wfplr;
	}

	public void setWfplr(Integer wfplr) {
		this.wfplr = wfplr;
	}

	public Integer getGsymgsgdqyhj() {
		return gsymgsgdqyhj;
	}

	public void setGsymgsgdqyhj(Integer gsymgsgdqyhj) {
		this.gsymgsgdqyhj = gsymgsgdqyhj;
	}

	public Integer getSyzqyhj() {
		return syzqyhj;
	}

	public void setSyzqyhj(Integer syzqyhj) {
		this.syzqyhj = syzqyhj;
	}

	@Id
	@Column(length=6)
	private String stockCode;
	
	@Id
	private Integer year;
	
	private Integer hbzj;
	
	private Integer yspj;
	
	private Integer yszk;
	
	private Integer yfkx;
	
	private Integer qtysk;
	
	private Integer ch;
	
	private Integer ldzchj;
	
	private Integer gdzc;

	private Integer zjgc;
	
	private Integer wxzc;
	
	private Integer sy;
	
	private Integer fldzchj;
	
	private Integer zczj;
	
	private Integer dqjk;
	
	private Integer yfpj;
	
	private Integer yfzk;
	
	private Integer ldfzhj;
	
	private Integer fldfzhj;
	
	private Integer fzhj;
	
	private Integer sszb;
	
	private Integer zbgj;
	
	private Integer yygj;
	
	private Integer wfplr;
	
	private Integer gsymgsgdqyhj;
	
	private Integer syzqyhj;

}
