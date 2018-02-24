package com.madongfang.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.madongfang.api.ReturnApi;
import com.madongfang.entity.BalanceSheet;
import com.madongfang.entity.CashFlowStatement;
import com.madongfang.entity.IncomeStatement;
import com.madongfang.exception.HttpBadRequestException;
import com.madongfang.exception.HttpInternalServerErrorException;
import com.madongfang.repository.BalanceSheetRepository;
import com.madongfang.repository.CashFlowStatementRepository;
import com.madongfang.repository.IncomeStatementRepository;

@Service
public class ImportService {

	public ReturnApi importIncomeStatement(MultipartFile zipFile) {
		if (zipFile.isEmpty())
		{
			throw new HttpBadRequestException(new ReturnApi(-1, "设备文件为空"));
		}
		
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(zipFile.getInputStream());
			ZipEntry zipEntry = null;
			while ((zipEntry = zis.getNextEntry()) != null)
			{
				String fileName = zipEntry.getName();
				if (zipEntry.isDirectory() || !fileName.matches("lrb(\\d){6}\\.csv"))
				{
					logger.warn("无效的文件:{}", fileName);
					continue;
				}
				String stockCode = fileName.substring(3, 9);
				logger.debug("文件:{},stockCode={}", fileName, stockCode);
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zis, "GB18030"));
				String line = bufferedReader.readLine().trim();
				if (line == null)
				{
					bufferedReader.close();
					throw new HttpBadRequestException(new ReturnApi(-2, "报表格式错误:line="+line));
				}
				else
				{
					if (line.equals("暂无数据"))
					{
						continue;
					}
					
					if (!line.startsWith("报告日期"))
					{
						bufferedReader.close();
						throw new HttpBadRequestException(new ReturnApi(-2, "报表格式错误:line="+line));
					}
				}
				
				String[] columns = line.split(",");
				int rowNum = 46;
				int columnNum = columns.length;
				String[][] table = new String[rowNum][columnNum];
				for (int j = 0; j < columnNum; j++)
				{
					table[0][j] = columns[j];
				}
				for (int i = 1; i < rowNum; i++)
				{
					line = bufferedReader.readLine().trim();
					logger.debug(line);
					if (line == null)
					{
						break;
					}
					columns = line.split(",");
					for (int j = 0; j < columnNum && j < columns.length; j++)
					{
						table[i][j] = columns[j];
					}
				}
				
				List<IncomeStatement> incomeStatements = new LinkedList<>();
				for (int j = 1; j < columnNum; j++)
				{
					if (table[0][j] == null || !table[0][j].matches("(\\d){4}-12-31"))
					{
						continue;
					}
					
					IncomeStatement incomeStatement = new IncomeStatement();
					incomeStatement.setStockCode(stockCode);
					incomeStatement.setYear(Integer.valueOf(table[0][j].substring(0, 4)));
					for (int i = 1; i < rowNum; i++)
					{
						Integer value = null;
						try {
							value = Integer.valueOf(table[i][j]);
						} catch (NumberFormatException e) {
							value = null;
						}
						
						switch (table[i][0]) {
						case "营业总收入(万元)":
							incomeStatement.setYyzsr(value);
							break;
						case "营业收入(万元)":
							incomeStatement.setYysr(value);
							break;
						case "营业总成本(万元)":
							incomeStatement.setYyzcb(value);
							break;
						case "营业成本(万元)":
							incomeStatement.setYycb(value);
							break;
						case "销售费用(万元)":
							incomeStatement.setXsfy(value);
							break;
						case "管理费用(万元)":
							incomeStatement.setGlfy(value);
							break;
						case "财务费用(万元)":
							incomeStatement.setCwfy(value);
							break;
						case "资产减值损失(万元)":
							incomeStatement.setZcjzss(value);
							break;
						case "投资收益(万元)":
							incomeStatement.setTzsy(value);
							break;
						case "营业利润(万元)":
							incomeStatement.setYylr(value);
							break;
						case "营业外收入(万元)":
							incomeStatement.setYywsr(value);
							break;
						case "营业外支出(万元)":
							incomeStatement.setYywzc(value);
							break;
						case "利润总额(万元)":
							incomeStatement.setLrze(value);
							break;
						case "净利润(万元)":
							incomeStatement.setJlr(value);
							break;
						case "归属于母公司所有者的净利润(万元)":
							incomeStatement.setGsymgssyzdjlr(value);
							break;
						default:
							break;
						}
					}
					
					incomeStatements.add(incomeStatement);
				}
				
				incomeStatementRepository.save(incomeStatements);

			}
			
			return new ReturnApi(0, "OK");
		} catch (IOException e) {
			// TODO: handle exception
			logger.error("catch IOException:", e);
			throw new HttpInternalServerErrorException(new ReturnApi(-2, e.getMessage()));
		} finally {
			if (zis != null)
			{
				try {
					zis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("catch IOException:", e);
				}
			}
		}
		
	}
	
	public ReturnApi importBalanceSheet(MultipartFile zipFile) {
		if (zipFile.isEmpty())
		{
			throw new HttpBadRequestException(new ReturnApi(-1, "设备文件为空"));
		}
		
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(zipFile.getInputStream());
			ZipEntry zipEntry = null;
			while ((zipEntry = zis.getNextEntry()) != null)
			{
				String fileName = zipEntry.getName();
				if (zipEntry.isDirectory() || !fileName.matches("zcfzb(\\d){6}\\.csv"))
				{
					logger.warn("无效的文件:{}", fileName);
					continue;
				}
				String stockCode = fileName.substring(5, 11);
				logger.debug("文件:{},stockCode={}", fileName, stockCode);
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zis, "GB18030"));
				String line = bufferedReader.readLine().trim();
				if (line == null)
				{
					bufferedReader.close();
					throw new HttpBadRequestException(new ReturnApi(-2, "报表格式错误:line="+line));
				}
				else
				{
					if (line.equals("暂无数据"))
					{
						continue;
					}
					
					if (!line.startsWith("报告日期"))
					{
						bufferedReader.close();
						throw new HttpBadRequestException(new ReturnApi(-2, "报表格式错误:line="+line));
					}
				}
				String[] columns = line.split(",");
				int rowNum = 109;
				int columnNum = columns.length;
				String[][] table = new String[rowNum][columnNum];
				for (int j = 0; j < columnNum; j++)
				{
					table[0][j] = columns[j];
				}
				for (int i = 1; i < rowNum; i++)
				{
					line = bufferedReader.readLine().trim();
					logger.debug(line);
					if (line == null)
					{
						break;
					}
					columns = line.split(",");
					for (int j = 0; j < columnNum && j < columns.length; j++)
					{
						table[i][j] = columns[j];
					}
				}
				
				List<BalanceSheet> balanceSheets = new LinkedList<>();
				for (int j = 1; j < columnNum; j++)
				{
					if (table[0][j] == null || !table[0][j].matches("(\\d){4}-12-31"))
					{
						continue;
					}
					
					BalanceSheet balanceSheet = new BalanceSheet();
					balanceSheet.setStockCode(stockCode);
					balanceSheet.setYear(Integer.valueOf(table[0][j].substring(0, 4)));
					for (int i = 1; i < rowNum; i++)
					{
						Integer value = null;
						try {
							value = Integer.valueOf(table[i][j]);
						} catch (NumberFormatException e) {
							value = null;
						}
						
						switch (table[i][0]) {
						case "货币资金(万元)":
							balanceSheet.setHbzj(value);
							break;
						case "应收票据(万元)":
							balanceSheet.setYspj(value);
						case "应收账款(万元)":
							balanceSheet.setYszk(value);
						case "预付款项(万元)":
							balanceSheet.setYfkx(value);
						case "其他应收款(万元)":
							balanceSheet.setQtysk(value);
						case "存货(万元)":
							balanceSheet.setCh(value);
						case "流动资产合计(万元)":
							balanceSheet.setLdzchj(value);
						case "固定资产(万元)":
							balanceSheet.setGdzc(value);
						case "在建工程(万元)":
							balanceSheet.setZjgc(value);
						case "无形资产(万元)":
							balanceSheet.setWxzc(value);
						case "商誉(万元)":
							balanceSheet.setSy(value);
						case "非流动资产合计(万元)":
							balanceSheet.setFldzchj(value);
						case "资产总计(万元)":
							balanceSheet.setZczj(value);
						case "短期借款(万元)":
							balanceSheet.setDqjk(value);
						case "应付票据(万元)":
							balanceSheet.setYfpj(value);
						case "应付账款(万元)":
							balanceSheet.setYfzk(value);
						case "流动负债合计(万元)":
							balanceSheet.setLdfzhj(value);
						case "非流动负债合计(万元)":
							balanceSheet.setFldfzhj(value);
						case "负债合计(万元)":
							balanceSheet.setFzhj(value);
						case "实收资本(或股本)(万元)":
							balanceSheet.setSszb(value);
						case "资本公积(万元)":
							balanceSheet.setZbgj(value);
						case "盈余公积(万元)":
							balanceSheet.setYygj(value);
						case "未分配利润(万元)":
							balanceSheet.setWfplr(value);
						case "归属于母公司股东权益合计(万元)":
							balanceSheet.setGsymgsgdqyhj(value);
						case "所有者权益(或股东权益)合计(万元)":
							balanceSheet.setSyzqyhj(value);
							break;
						default:
							break;
						}
					}
					
					balanceSheets.add(balanceSheet);
				}
				
				balanceSheetRepository.save(balanceSheets);

			}
			
			return new ReturnApi(0, "OK");
		} catch (IOException e) {
			// TODO: handle exception
			logger.error("catch IOException:", e);
			throw new HttpInternalServerErrorException(new ReturnApi(-2, e.getMessage()));
		} finally {
			if (zis != null)
			{
				try {
					zis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("catch IOException:", e);
				}
			}
		}
		
	}
	
	public ReturnApi importCashFlowStatement(MultipartFile zipFile) {
		if (zipFile.isEmpty())
		{
			throw new HttpBadRequestException(new ReturnApi(-1, "设备文件为空"));
		}
		
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(zipFile.getInputStream());
			ZipEntry zipEntry = null;
			while ((zipEntry = zis.getNextEntry()) != null)
			{
				String fileName = zipEntry.getName();
				if (zipEntry.isDirectory() || !fileName.matches("xjllb(\\d){6}\\.csv"))
				{
					logger.warn("无效的文件:{}", fileName);
					continue;
				}
				String stockCode = fileName.substring(5, 11);
				logger.debug("文件:{},stockCode={}", fileName, stockCode);
				
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zis, "GB18030"));
				String line = bufferedReader.readLine().trim();
				if (line == null)
				{
					bufferedReader.close();
					throw new HttpBadRequestException(new ReturnApi(-2, "报表格式错误:line="+line));
				}
				else
				{
					if (line.equals("暂无数据"))
					{
						continue;
					}
					
					if (!line.startsWith("报告日期"))
					{
						bufferedReader.close();
						throw new HttpBadRequestException(new ReturnApi(-2, "报表格式错误:line="+line));
					}
				}
				String[] columns = line.split(",");
				int rowNum = 90;
				int columnNum = columns.length;
				String[][] table = new String[rowNum][columnNum];
				for (int j = 0; j < columnNum; j++)
				{
					table[0][j] = columns[j];
				}
				for (int i = 1; i < rowNum; i++)
				{
					line = bufferedReader.readLine().trim();
					logger.debug(line);
					if (line == null)
					{
						break;
					}
					columns = line.split(",");
					for (int j = 0; j < columnNum && j < columns.length; j++)
					{
						table[i][j] = columns[j];
					}
				}
				
				List<CashFlowStatement> cashFlowStatements = new LinkedList<>();
				for (int j = 1; j < columnNum; j++)
				{
					if (table[0][j] == null || !table[0][j].matches("(\\d){4}-12-31"))
					{
						continue;
					}
					
					CashFlowStatement cashFlowStatement = new CashFlowStatement();
					cashFlowStatement.setStockCode(stockCode);
					cashFlowStatement.setYear(Integer.valueOf(table[0][j].substring(0, 4)));
					for (int i = 1; i < rowNum; i++)
					{
						Integer value = null;
						try {
							value = Integer.valueOf(table[i][j]);
						} catch (NumberFormatException e) {
							value = null;
						}
						
						switch (table[i][0]) {
						case "经营活动现金流入小计(万元)":
							cashFlowStatement.setJyhdxjlrxj(value);
							break;
						case "经营活动现金流出小计(万元)":
							cashFlowStatement.setJyhdxjlcxj(value);
							break;
						case "经营活动产生的现金流量净额(万元)":
							cashFlowStatement.setJyhdcsdxjllje(value);
							break;
						case "投资活动现金流入小计(万元)":
							cashFlowStatement.setTzhdxjlrxj(value);
							break;
						case "投资活动现金流出小计(万元)":
							cashFlowStatement.setTzhdxjlcxj(value);
							break;
						case "投资活动产生的现金流量净额(万元)":
							cashFlowStatement.setTzhdcsdxjllje(value);
							break;
						case "筹资活动现金流入小计(万元)":
							cashFlowStatement.setCzhdxjlrxj(value);
							break;
						case "筹资活动现金流出小计(万元)":
							cashFlowStatement.setCzhdxjlcxj(value);
							break;
						case "筹资活动产生的现金流量净额(万元)":
							cashFlowStatement.setCzhdcsdxjllje(value);
							break;
						case "期末现金及现金等价物余额(万元)":
							cashFlowStatement.setQmxjjxjdjwye(value);
							break;
						default:
							break;
						}
					}
					
					cashFlowStatements.add(cashFlowStatement);
				}
				
				cashFlowStatementRepository.save(cashFlowStatements);

			}
			
			return new ReturnApi(0, "OK");
		} catch (IOException e) {
			// TODO: handle exception
			logger.error("catch IOException:", e);
			throw new HttpInternalServerErrorException(new ReturnApi(-2, e.getMessage()));
		} finally {
			if (zis != null)
			{
				try {
					zis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("catch IOException:", e);
				}
			}
		}
		
	}
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IncomeStatementRepository incomeStatementRepository;
	
	@Autowired
	private BalanceSheetRepository balanceSheetRepository;
	
	@Autowired
	private CashFlowStatementRepository cashFlowStatementRepository;
}
