package com.madongfang.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.ProfitApi;
import com.madongfang.service.DataService;

@RestController
@RequestMapping(value="/api/echarts")
public class EchartsController {

	@GetMapping(value="/timeDatas", params="type=pe")
	public List<List<Object>> getPeDatas(@RequestParam String stock, 
			@RequestParam(name="beginDate", required=false) Long beginDateMs, 
			@RequestParam(name="endDate", required=false) Long endDateMs,
			@RequestParam(required=false)Double netProfit)
	{
		Date beginDate = new Date(0);
		Date endDate = new Date();
		if (beginDateMs != null)
		{
			beginDate.setTime(beginDateMs);
		}
		if (endDateMs != null)
		{
			endDate.setTime(endDateMs);
		}
		
		return dataService.getPeDatas(stock, beginDate, endDate, netProfit);
	}
	
	@GetMapping(value="/timeDatas", params="type=po")
	public List<List<Object>> getPoDatas(@RequestParam String stock, 
			@RequestParam(name="beginDate", required=false) Long beginDateMs, 
			@RequestParam(name="endDate", required=false) Long endDateMs,
			@RequestParam(required=false)Double operatingProfit)
	{
		Date beginDate = new Date(0);
		Date endDate = new Date();
		if (beginDateMs != null)
		{
			beginDate.setTime(beginDateMs);
		}
		if (endDateMs != null)
		{
			endDate.setTime(endDateMs);
		}
		
		return dataService.getPoDatas(stock, beginDate, endDate, operatingProfit);
	}
	
	@GetMapping(params="type=profit")
	public ProfitApi getProfit(@RequestParam String stock)
	{
		return dataService.getProfit(stock);
	}
	
	@Autowired
	private DataService dataService;
}
