package com.tianruan.dao;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.TDepartment;
import com.tianruan.model.TWorkRules;

public class WorkRulesDao extends CommonDAO{

	public List getAllWorkRulesList()
	{
		String hql = "select u from TWorkRules u";
		return super.getObjectList(hql);
	}
	public List getAllWorkRulesList(Page pager,String searchName)
	{
		StringBuffer bufferSQL = new StringBuffer();		
		bufferSQL.append("select u from TWorkRules u ");	
		if(null!=searchName && !"".equals(searchName))
		{
			bufferSQL.append(" where u.TStartTime like '%"+searchName+"%' ");
		}		
		bufferSQL.append(" order by u.TStartTime");		
		return super.getObjectList(bufferSQL.toString(),pager);
	}
	public boolean addOrUpdate(TWorkRules workRules){
		return super.addOrUpdate(workRules);
	}
	
	public boolean deleteWorkRules(TWorkRules workRules){
		return super.deleteObj(workRules);
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
}
