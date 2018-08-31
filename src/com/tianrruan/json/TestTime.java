package com.tianrruan.json;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class TestTime {

	@Test
	public void test2()
	{
		System.out.println(UUID.randomUUID().toString());
	}
	@Test
	public void test1()
	{
		String time = "1474358587933";
		String.valueOf(System.currentTimeMillis());
		 Date nowTime = new Date(System.currentTimeMillis());
		  SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  
		  String retStrFormatNowDate = sdFormatter.format(Long.valueOf(time));
		  
		  System.out.println(retStrFormatNowDate+"---"+System.currentTimeMillis());
	}
}
