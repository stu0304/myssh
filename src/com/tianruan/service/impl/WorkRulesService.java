package com.tianruan.service.impl;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.WorkRulesDao;
import com.tianruan.model.Page;
import com.tianruan.model.TPerson;
import com.tianruan.model.TWorkRules;
import com.tianruan.service.IWorkRulesService;

public class WorkRulesService implements IWorkRulesService{
	
	private WorkRulesDao workRulesDao;
	
	@Override
	public List loadAllWorkRules() {
		
		return workRulesDao.getAllWorkRulesList();
	}

	@Override
	public String loadAllWorkRulesJSON(Page pager, String searchName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		obj.add("rows", new JsonParser().parse(gson.toJson(workRulesDao.getAllWorkRulesList(pager,searchName))));

		return obj.toString();
	}

	@Override
	public boolean addOrUpdate(TWorkRules workRules) {	
		return workRulesDao.addOrUpdate(workRules);
	}
	
	@Override
	public boolean deleteWorkRules(TWorkRules workRules) {
		return workRulesDao.deleteObj(workRules);
	}

	@Override
	public String countNum() {
		String hql = "select count(u) from TWorkRules u";
		return String.valueOf(workRulesDao.countNum(hql));
	}
	public WorkRulesDao getWorkRulesDao() {
		return workRulesDao;
	}
	public void setWorkRulesDao(WorkRulesDao workRulesDao) {
		this.workRulesDao = workRulesDao;
	}

}
