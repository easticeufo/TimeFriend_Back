package com.madongfang.repository;

public class AnnualReportRepos {

	public AnnualReportRepos() {
		super();
	}

	public AnnualReportRepos(String stockCode, Integer year, Integer yysr, Integer yycb, Integer yylr,
			Integer gsymgssyzdjlr, Integer hbzj, Integer yszk, Integer ch, Integer zczj, Integer ldfzhj,
			Integer jyhdcsdxjllje) {
		super();
		this.stockCode = stockCode;
		this.year = year;
		this.yysr = yysr;
		this.yycb = yycb;
		this.yylr = yylr;
		this.gsymgssyzdjlr = gsymgssyzdjlr;
		this.hbzj = hbzj;
		this.yszk = yszk;
		this.ch = ch;
		this.zczj = zczj;
		this.ldfzhj = ldfzhj;
		this.jyhdcsdxjllje = jyhdcsdxjllje;
	}

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

	public Integer getYysr() {
		return yysr;
	}

	public void setYysr(Integer yysr) {
		this.yysr = yysr;
	}

	public Integer getYycb() {
		return yycb;
	}

	public void setYycb(Integer yycb) {
		this.yycb = yycb;
	}

	public Integer getYylr() {
		return yylr;
	}

	public void setYylr(Integer yylr) {
		this.yylr = yylr;
	}

	public Integer getGsymgssyzdjlr() {
		return gsymgssyzdjlr;
	}

	public void setGsymgssyzdjlr(Integer gsymgssyzdjlr) {
		this.gsymgssyzdjlr = gsymgssyzdjlr;
	}

	public Integer getHbzj() {
		return hbzj;
	}

	public void setHbzj(Integer hbzj) {
		this.hbzj = hbzj;
	}

	public Integer getYszk() {
		return yszk;
	}

	public void setYszk(Integer yszk) {
		this.yszk = yszk;
	}

	public Integer getCh() {
		return ch;
	}

	public void setCh(Integer ch) {
		this.ch = ch;
	}

	public Integer getZczj() {
		return zczj;
	}

	public void setZczj(Integer zczj) {
		this.zczj = zczj;
	}

	public Integer getLdfzhj() {
		return ldfzhj;
	}

	public void setLdfzhj(Integer ldfzhj) {
		this.ldfzhj = ldfzhj;
	}

	public Integer getJyhdcsdxjllje() {
		return jyhdcsdxjllje;
	}

	public void setJyhdcsdxjllje(Integer jyhdcsdxjllje) {
		this.jyhdcsdxjllje = jyhdcsdxjllje;
	}

	@Override
	public String toString() {
		return "AnnualReportRepos [stockCode=" + stockCode + ", year=" + year + ", yysr=" + yysr + ", yycb=" + yycb
				+ ", yylr=" + yylr + ", gsymgssyzdjlr=" + gsymgssyzdjlr + ", hbzj=" + hbzj + ", yszk=" + yszk + ", ch="
				+ ch + ", zczj=" + zczj + ", ldfzhj=" + ldfzhj + ", jyhdcsdxjllje=" + jyhdcsdxjllje + "]";
	}

	private String stockCode;
	
	private Integer year;
	
	private Integer yysr;
	
	private Integer yycb;
	
	private Integer yylr;
	
	private Integer gsymgssyzdjlr;
	
	private Integer hbzj;

	private Integer yszk;
	
	private Integer ch;
	
	private Integer zczj;
	
	private Integer ldfzhj;
	
	private Integer jyhdcsdxjllje;
}
