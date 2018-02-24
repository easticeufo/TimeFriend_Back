package com.madongfang.service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.madongfang.api.ReturnApi;
import com.madongfang.api.StockApi;
import com.madongfang.exception.HttpInternalServerErrorException;
import com.madongfang.exception.HttpNotFoundException;
import com.madongfang.repository.AnnualReportRepos;
import com.madongfang.repository.IncomeStatementRepository;
import com.madongfang.util.HttpUtil;

@Service
public class StockService {

	public StockApi getStock(String stockCode) {
		try {
			StockApi stockApi = null;
			
			String url = String.format("http://quotes.money.163.com/stocksearch/json.do?count=1&word=%s", stockCode);
			String string = httpUtil.getToString(url);
			int i = string.indexOf("{");
			int j = string.indexOf("}");
			if (i != -1 && j != -1)
			{
				stockApi = new ObjectMapper().readValue(string.substring(i, j+1), StockApi.class);
				stockApi.setStockCode(stockCode);
				return stockApi;
			}
			else
			{
				throw new HttpNotFoundException(new ReturnApi(-2, "无效的股票代码"));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Catch IOException:", e);
			throw new HttpInternalServerErrorException(new ReturnApi(-1, e.getMessage()));
		}
		
		
	}
	
	public List<StockApi> getStocks(Integer startYear, Integer minGrowthRate)
	{
		List<StockApi> stocks = new LinkedList<>();
		
		List<AnnualReportRepos> annualReports = incomeStatementRepository.getAnnualReports();
		
		String stockCode = "";
		List<AnnualReportRepos> stockAnnualReports = new LinkedList<>();
		for (AnnualReportRepos annualReport : annualReports) {
			if (!annualReport.getStockCode().equals(stockCode))
			{
				stockCode = annualReport.getStockCode();
				StockApi stockApi = checkAndStockApi(startYear, minGrowthRate, stockAnnualReports);
				if (stockApi != null)
				{
					stocks.add(stockApi);
				}
				stockAnnualReports.clear();
			}
			
			stockAnnualReports.add(annualReport);
		}
		
		StockApi stockApi = checkAndStockApi(startYear, minGrowthRate, stockAnnualReports);
		if (stockApi != null)
		{
			stocks.add(stockApi);
		}
		
		return stocks;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HttpUtil httpUtil;
	
	@Autowired
	private IncomeStatementRepository incomeStatementRepository;
	
	private StockApi checkAndStockApi(Integer startYear, Integer minGrowthRate, List<AnnualReportRepos> annualReports)
	{
		if (annualReports.size() == 0)
		{
			return null;
		}
		
		AnnualReportRepos lastAnnualReport = annualReports.get(0);
		Integer lastYear = lastAnnualReport.getYear();
		if (startYear == null)
		{
			startYear = lastYear + 1;
		}
		else
		{
			if (startYear < lastYear + 1)
			{
				return null;
			}
		}
		for (int i = 1; i < annualReports.size(); i++) {
			AnnualReportRepos annualReport = annualReports.get(i);
			if (annualReport.getYear() < startYear)
			{
				lastAnnualReport = annualReport;
				continue;
			}
			
			if (minGrowthRate != null)
			{
				if (annualReport.getYylr() == null || annualReport.getGsymgssyzdjlr() == null)
				{
					logger.debug("营业利润或净利润为null:stockCode={},year={}", annualReport.getStockCode(), annualReport.getYear());
					return null;
				}
				
				if (lastAnnualReport.getYylr() == null || lastAnnualReport.getGsymgssyzdjlr() == null
						|| lastAnnualReport.getYylr() == 0 || lastAnnualReport.getGsymgssyzdjlr() == 0)
				{
					logger.debug("前一年的营业利润或净利润增无法计算:stockCode={},year={}", lastAnnualReport.getStockCode(), lastAnnualReport.getYear());
					return null;
				}
				
				int growthRate = (annualReport.getYylr() - lastAnnualReport.getYylr()) * 100 / lastAnnualReport.getYylr();
				if (growthRate < minGrowthRate)
				{
					logger.debug("营业利润增长率不达标:stockCode={},year={},growthRate={}", annualReport.getStockCode(), annualReport.getYear(), growthRate);
					return null;
				}
				
				growthRate = (annualReport.getGsymgssyzdjlr() - lastAnnualReport.getGsymgssyzdjlr()) * 100 / lastAnnualReport.getGsymgssyzdjlr();
				if (growthRate < minGrowthRate)
				{
					logger.debug("净利润增长率不达标:stockCode={},year={},growthRate={}", annualReport.getStockCode(), annualReport.getYear(), growthRate);
					return null;
				}
			}
			
			lastAnnualReport = annualReport;
		}
		
		if (lastAnnualReport.getYear() < startYear)
		{
			logger.debug("最新的财务报表年份小于开始年份:stockCode={},year={}", lastAnnualReport.getStockCode(), lastAnnualReport.getYear());
			return null;
		}
		
		StockApi stockApi = new StockApi();
		stockApi.setStockCode(lastAnnualReport.getStockCode());
		if (lastAnnualReport.getHbzj() != null && lastAnnualReport.getZczj() != null)
		{
			stockApi.setHbzjzb((double)lastAnnualReport.getHbzj() * 100 / lastAnnualReport.getZczj());
		}
		if (lastAnnualReport.getJyhdcsdxjllje() != null && lastAnnualReport.getGsymgssyzdjlr() != null)
		{
			stockApi.setJlrhjl((double)lastAnnualReport.getJyhdcsdxjllje() / lastAnnualReport.getGsymgssyzdjlr());
		}
		if (lastAnnualReport.getYysr() != null && lastAnnualReport.getYycb() != null)
		{
			stockApi.setMlv((double)(lastAnnualReport.getYysr() - lastAnnualReport.getYycb()) * 100 / lastAnnualReport.getYysr());
		}
		if (lastAnnualReport.getYysr() != null && lastAnnualReport.getZczj() != null)
		{
			stockApi.setZzczzl((double)lastAnnualReport.getYysr() / lastAnnualReport.getZczj());
		}
		
		return stockApi;
	}
}
