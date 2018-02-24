package com.madongfang.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StatementPK.class)
public class IncomeStatement {

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

	public Integer getYyzsr() {
		return yyzsr;
	}

	public void setYyzsr(Integer yyzsr) {
		this.yyzsr = yyzsr;
	}

	public Integer getYysr() {
		return yysr;
	}

	public void setYysr(Integer yysr) {
		this.yysr = yysr;
	}

	public Integer getYyzcb() {
		return yyzcb;
	}

	public void setYyzcb(Integer yyzcb) {
		this.yyzcb = yyzcb;
	}

	public Integer getYycb() {
		return yycb;
	}

	public void setYycb(Integer yycb) {
		this.yycb = yycb;
	}

	public Integer getXsfy() {
		return xsfy;
	}

	public void setXsfy(Integer xsfy) {
		this.xsfy = xsfy;
	}

	public Integer getGlfy() {
		return glfy;
	}

	public void setGlfy(Integer glfy) {
		this.glfy = glfy;
	}

	public Integer getCwfy() {
		return cwfy;
	}

	public void setCwfy(Integer cwfy) {
		this.cwfy = cwfy;
	}

	public Integer getZcjzss() {
		return zcjzss;
	}

	public void setZcjzss(Integer zcjzss) {
		this.zcjzss = zcjzss;
	}

	public Integer getTzsy() {
		return tzsy;
	}

	public void setTzsy(Integer tzsy) {
		this.tzsy = tzsy;
	}

	public Integer getYylr() {
		return yylr;
	}

	public void setYylr(Integer yylr) {
		this.yylr = yylr;
	}

	public Integer getYywsr() {
		return yywsr;
	}

	public void setYywsr(Integer yywsr) {
		this.yywsr = yywsr;
	}

	public Integer getYywzc() {
		return yywzc;
	}

	public void setYywzc(Integer yywzc) {
		this.yywzc = yywzc;
	}

	public Integer getLrze() {
		return lrze;
	}

	public void setLrze(Integer lrze) {
		this.lrze = lrze;
	}

	public Integer getJlr() {
		return jlr;
	}

	public void setJlr(Integer jlr) {
		this.jlr = jlr;
	}

	public Integer getGsymgssyzdjlr() {
		return gsymgssyzdjlr;
	}

	public void setGsymgssyzdjlr(Integer gsymgssyzdjlr) {
		this.gsymgssyzdjlr = gsymgssyzdjlr;
	}

	@Id
	@Column(length=6)
	private String stockCode;
	
	@Id
	private Integer year;
	
	private Integer yyzsr; // 营业总收入(万元)
	
	private Integer yysr; // 营业收入(万元)
	
	private Integer yyzcb;
	
	private Integer yycb;
	
	private Integer xsfy;
	
	private Integer glfy;
	
	private Integer cwfy;
	
	private Integer zcjzss;
	
	private Integer tzsy;
	
	private Integer yylr;
	
	private Integer yywsr;
	
	private Integer yywzc;
	
	private Integer lrze;
	
	private Integer jlr;
	
	private Integer gsymgssyzdjlr;
}
