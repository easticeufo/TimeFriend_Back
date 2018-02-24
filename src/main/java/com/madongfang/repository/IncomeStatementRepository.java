package com.madongfang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.madongfang.entity.IncomeStatement;
import com.madongfang.entity.StatementPK;

public interface IncomeStatementRepository extends JpaRepository<IncomeStatement, StatementPK> {

	@Query("select new com.madongfang.repository.AnnualReportRepos(i.stockCode,i.year,i.yysr,i.yycb,i.yylr,"
			+ "i.gsymgssyzdjlr,b.hbzj,b.yszk,b.ch,b.zczj,b.ldfzhj,c.jyhdcsdxjllje)"
			+ " from IncomeStatement i, BalanceSheet b, CashFlowStatement c"
			+ " where i.stockCode = b.stockCode and i.year = b.year and i.stockCode = c.stockCode and i.year = c.year"
			+ " order by i.stockCode asc, i.year asc")
	public List<AnnualReportRepos> getAnnualReports();
	
	public List<IncomeStatement> findByStockCodeOrderByYearAsc(String stockCode);
}
