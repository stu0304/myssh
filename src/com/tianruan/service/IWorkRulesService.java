package com.tianruan.service;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.TWorkRules;

public interface IWorkRulesService {
	
	public String loadAllWorkRulesJSON(Page pager, String searchName);
	
	public boolean addOrUpdate(TWorkRules workRules);
	
	public boolean deleteWorkRules(TWorkRules workRules);
	
	public String countNum();
	
	public List loadAllWorkRules();
}
