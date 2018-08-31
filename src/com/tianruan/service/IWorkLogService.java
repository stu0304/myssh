package com.tianruan.service;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.TWorkLog;

public interface IWorkLogService {
	
	public String loadAllWorkLogJSON(Page pager, String searchName,String personid);
	
	public boolean addOrUpdate(TWorkLog workLog);
	
	public boolean Update(TWorkLog workLog);
	
	public boolean deleteWorkLog(TWorkLog workLog);
	
	public String countNum();
	
	public boolean addAllPersonAndLog(String allYMonth,int remainderDay,int day,String personid);
	
	public List loadAllWorkLog(String personid,String allYMDay);
	
	public String loadAllWorkLogSumJSON(Page pager);
	
	public String getworkLogColumn();
}
