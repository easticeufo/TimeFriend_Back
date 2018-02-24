package com.madongfang.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.madongfang.api.ReturnApi;
import com.madongfang.service.ImportService;

@RestController
@RequestMapping(value="/api/import")
public class ImportController {

	@PostMapping(value="/incomeStatement")
	public ReturnApi importIncomeStatement(@RequestParam("zipFile") MultipartFile zipFile) throws IOException
	{
		return importService.importIncomeStatement(zipFile);
	}
	
	@PostMapping(value="/balanceSheet")
	public ReturnApi importBalanceSheet(@RequestParam("zipFile") MultipartFile zipFile) throws IOException
	{
		return importService.importBalanceSheet(zipFile);
	}
	
	@PostMapping(value="/cashFlowStatement")
	public ReturnApi importCashFlowStatement(@RequestParam("zipFile") MultipartFile zipFile) throws IOException
	{
		return importService.importCashFlowStatement(zipFile);
	}
	
	@Autowired
	private ImportService importService;
}
