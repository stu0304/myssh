package com.tianruan.util;

import java.text.SimpleDateFormat;

public class DateUtil {

	public static String getNowTime()
	{
		return String.valueOf(System.currentTimeMillis());
	}
	
	public static String getWebTime(String myTime,String strFormat)
	{
			SimpleDateFormat sdFormatter = new SimpleDateFormat(strFormat);
		    return  sdFormatter.format(Long.valueOf(myTime));
	}
}
