package com.madongfang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.api.StockApi;
import com.madongfang.service.StockService;

@RestController
@RequestMapping(value="/api/stocks")
public class StockController {

	@GetMapping
	public List<StockApi> getStocks(@RequestParam(required=false) Integer startYear, 
			@RequestParam(required=false) Integer minGrowthRate)
	{
		return stockService.getStocks(startYear, minGrowthRate);
	}
	
	@GetMapping(value="/{stockCode}")
	public StockApi getStock(@PathVariable String stockCode) {
		return stockService.getStock(stockCode);
	}
	
	@Autowired
	private StockService stockService;
}
