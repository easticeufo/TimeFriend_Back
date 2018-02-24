package com.madongfang.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madongfang.util.HttpUtil;

@RestController
@RequestMapping(value="/api/test")
public class TestController {

	@GetMapping
	public void getScv()
	{
		try {
			httpUtil.getToString("http://quotes.money.163.com/service/chddata.html?code=1002415&start=20100528&end=20170917&fields=TCAP");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Autowired
	private HttpUtil httpUtil;
	
}
