package com.madongfang.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.madongfang.api.ProfitApi;
import com.madongfang.api.ReturnApi;
import com.madongfang.entity.IncomeStatement;
import com.madongfang.exception.HttpInternalServerErrorException;
import com.madongfang.repository.IncomeStatementRepository;
import com.madongfang.util.HttpUtil;

@Service
public class DataService {

	public List<List<Object>> getPeDatas(String stock, Date beginDate, Date endDate, Double netProfitForecast)
	{
		try {
			List<List<Object>> datas = new LinkedList<List<Object>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			
			Map<String, Double> netProfitMap = new HashMap<>();
			if (netProfitForecast != null)
			{
				netProfitMap.put(sdf.format(new Date()), netProfitForecast * 10000 * 10000);
			}

			List<IncomeStatement> incomeStatements = incomeStatementRepository.findByStockCodeOrderByYearAsc(stock);
			for (IncomeStatement incomeStatement : incomeStatements) {
				netProfitMap.put(String.valueOf(incomeStatement.getYear()), (double)incomeStatement.getGsymgssyzdjlr() * 10000);
			}
			logger.debug("netProfitMap:{}", netProfitMap);
			
			String stockCode = null;
			if (stock.startsWith("6"))
			{
				stockCode = "0" + stock;
			}
			else
			{
				stockCode = "1" + stock;
			}
			sdf.applyPattern("yyyyMMdd");
			String csvString = httpUtil.getToString(String.format("http://quotes.money.163.com/service/chddata.html?code=%s&start=%s&end=%s&fields=TCAP", stockCode, sdf.format(beginDate), sdf.format(endDate)));
			String[][] dataTable = csvToTable(csvString);

			for (int i = 1; i < dataTable.length; i++)
			{
				if (dataTable[i].length >= 4)
				{
					List<Object> data = new ArrayList<Object>(2);
					data.add(dataTable[i][0]);
					String year = dataTable[i][0].substring(0, 4);
					
					Double netProfit = netProfitMap.get(year);
					if (netProfit != null)
					{
						data.add(String.format("%.2f", Double.valueOf(dataTable[i][3]) / netProfit));
						datas.add(data);
					}
				}
				
			}
			
			return datas;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Catch IOException:", e);
			throw new HttpInternalServerErrorException(new ReturnApi(-1, e.getMessage()));
		}
		
	}
	
	public List<List<Object>> getPoDatas(String stock, Date beginDate, Date endDate, Double operatingProfitForecast)
	{
		try {
			List<List<Object>> datas = new LinkedList<List<Object>>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			
			Map<String, Double> operatingProfitMap = new HashMap<>();
			if (operatingProfitForecast != null)
			{
				operatingProfitMap.put(sdf.format(new Date()), operatingProfitForecast * 10000 * 10000);
			}
			
			List<IncomeStatement> incomeStatements = incomeStatementRepository.findByStockCodeOrderByYearAsc(stock);
			for (IncomeStatement incomeStatement : incomeStatements) {
				operatingProfitMap.put(String.valueOf(incomeStatement.getYear()), (double)incomeStatement.getYylr() * 10000);
			}
			logger.debug("operatingProfitMap:{}", operatingProfitMap);
			
			String stockCode = null;
			if (stock.startsWith("6"))
			{
				stockCode = "0" + stock;
			}
			else
			{
				stockCode = "1" + stock;
			}
			sdf.applyPattern("yyyyMMdd");
			String csvString = httpUtil.getToString(String.format("http://quotes.money.163.com/service/chddata.html?code=%s&start=%s&end=%s&fields=TCAP", stockCode, sdf.format(beginDate), sdf.format(endDate)));
			String[][] dataTable = csvToTable(csvString);

			for (int i = 1; i < dataTable.length; i++)
			{
				if (dataTable[i].length >= 4)
				{
					List<Object> data = new ArrayList<Object>(2);
					data.add(dataTable[i][0]);
					String year = dataTable[i][0].substring(0, 4);
					
					Double operatingProfit = operatingProfitMap.get(year);
					if (operatingProfit != null)
					{
						data.add(String.format("%.2f", Double.valueOf(dataTable[i][3]) / operatingProfit));
						datas.add(data);
					}
				}
				
			}
			
			return datas;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Catch IOException:", e);
			throw new HttpInternalServerErrorException(new ReturnApi(-1, e.getMessage()));
		}
		
	}
	
	public ProfitApi getProfit(String stock) {
		ProfitApi profitApi = new ProfitApi();
		
		List<String> years = new LinkedList<>();
		List<Integer> operatingProfits = new LinkedList<>();
		List<Integer> netProfits = new LinkedList<>();
		List<String> operatingProfitRates = new LinkedList<>();
		List<String> netProfitRates = new LinkedList<>();
		
		List<IncomeStatement> incomeStatements = incomeStatementRepository.findByStockCodeOrderByYearAsc(stock);
		for (IncomeStatement incomeStatement : incomeStatements) {
			String year = String.valueOf(incomeStatement.getYear());
			Integer operatingProfit = incomeStatement.getYylr();
			Integer netProfit = incomeStatement.getGsymgssyzdjlr();
			years.add(year);
			if (operatingProfits.isEmpty())
			{
				operatingProfitRates.add("");
			}
			else
			{
				Integer lastOperatingProfit = operatingProfits.get(operatingProfits.size() - 1);
				operatingProfitRates.add(String.format("%.2f", (float)(operatingProfit - lastOperatingProfit) * 100 / lastOperatingProfit));
			}
			if (netProfits.isEmpty())
			{
				netProfitRates.add("");
			}
			else
			{
				Integer lastNetProfit = netProfits.get(netProfits.size() - 1);
				netProfitRates.add(String.format("%.2f", (float)(netProfit - lastNetProfit) * 100 / lastNetProfit));
			}
			operatingProfits.add(operatingProfit);
			netProfits.add(netProfit);
		}
		
		profitApi.setNetProfitRates(netProfitRates);
		profitApi.setNetProfits(netProfits);
		profitApi.setOperatingProfitRates(operatingProfitRates);
		profitApi.setOperatingProfits(operatingProfits);
		profitApi.setYears(years);
		return profitApi;
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HttpUtil httpUtil;

	@Autowired
	private IncomeStatementRepository incomeStatementRepository;
	
	private String[][] csvToTable(String csvString)
	{
		String[][] table;
		String[] rows = csvString.split("\n");
		table = new String[rows.length][];
		for (int i = 0; i < table.length; i++)
		{
			String[] columns = rows[i].split(",");
			table[i] = new String[columns.length];
			for (int j = 0; j < table[i].length; j++)
			{
				table[i][j] = columns[j];
			}
		}
		
		return table;
	}
}
