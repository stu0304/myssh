package com.tianruan.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianruan.model.RRoleModule;
import com.tianruan.model.Page;
import com.tianruan.model.TModules;


public class ModulesDao extends CommonDAO{
	
	public boolean addRoleModule(RRoleModule roleModule){
		return super.addObject(roleModule);
	}
	public List getAllModulesList(Page pager,String searchName)
	{
		
		StringBuffer bufferSQL = new StringBuffer();		
		bufferSQL.append("select m from TModules m ");	
		if(null!=searchName&&!"".equals(searchName))
		{
			bufferSQL.append(" where m.TModuleName like '%"+searchName+"%' ");
		}		
		bufferSQL.append(" order by m.TModuleName");
		
		return super.getObjectList(bufferSQL.toString());
	}
	public boolean addOrUpdate(TModules modules){
		return super.addOrUpdate(modules);
	}
	
	public boolean deleteModules(TModules modules){
		return super.deleteObj(modules);
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
}
