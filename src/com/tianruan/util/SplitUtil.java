package com.tianruan.util;

public class SplitUtil {
	
	public static Integer splitTime(String myTime) {
		String[] str = myTime.split(":");
		String value=str[0]+str[1];
		return Integer.valueOf(value);
	}
	public static Integer splitDay(String leaveTime){
		String[] str = leaveTime.split("-");
		String value=str[2];//天
		return Integer.valueOf(value);
	}
	public static String splitYMonth(String leaveTime){
		String[] str = leaveTime.split("-");
		String value=str[0]+str[1];//年月
		return value;
	}
	public static String splitMyTime(String myTime) {
		if(myTime.equals("0")){
			myTime="0";
		}else if(myTime.equals("1")){
			myTime="1";
		}else if(myTime.equals("2")){
			myTime="假";
		}else {
			String[] str = myTime.split(":");
			myTime=str[0]+"点"+str[1];		
		}
		return myTime;
	}
}
