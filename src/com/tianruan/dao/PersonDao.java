package com.tianruan.dao;

import java.util.List;


import com.tianruan.model.TPerson;
import com.tianruan.model.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PersonDao extends CommonDAO{
	
	public List getPersonIdByPerson(String personName) {
		String hql = "select p from TPerson p where p.TPersonName='"+personName+"' ";
		return super.getObjectList(hql);
	}
	
	public List getPersonByPersonId(String personId) {
		String hql = "select p from TPerson p where p.TPersonId='"+personId+"' ";
		return super.getObjectList(hql);
	}
	
	public List getAllSysUserList(Page pager,String searchName)
	{
		StringBuffer bufferSQL = new StringBuffer();		
		bufferSQL.append("select u from TPerson u ");	
		if(null!=searchName&&!"".equals(searchName))
		{
			bufferSQL.append(" where u.TPersonName like '%"+searchName+"%' ");
		}		
		bufferSQL.append(" order by u.TPersonName");
		
		return super.getObjectList(bufferSQL.toString(),pager);
	}
	
	public List<TPerson> getAllPersonList()
	{
		StringBuffer bufferSQL = new StringBuffer();		
		bufferSQL.append("select u from TPerson u ");	
		
		return super.getObjectList(bufferSQL.toString());
	}
	
	public boolean addOrUpdate(TPerson user){
		return super.addOrUpdate(user);
	}
	
	public boolean deleteUser(TPerson user){
		return super.deleteObj(user);
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
}
