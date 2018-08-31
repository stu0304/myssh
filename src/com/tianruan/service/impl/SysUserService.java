package com.tianruan.service.impl;

import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.SysUserDao;
import com.tianruan.model.Page;
import com.tianruan.model.TSysUser;
import com.tianruan.service.ISysUserService;

public class SysUserService implements ISysUserService{

	private SysUserDao sysUserDao;
	
	@Override
	public boolean deleteSysUser(TSysUser user) {
		return sysUserDao.deleteObj(user);
	}
	
	
	
	@Override
	public String loadAllSysUserJSON(Page pager,String searchName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		
		List userList = sysUserDao.getAllSysUserList(pager,searchName);
		Iterator iterator = userList.iterator();
		JsonArray elementArray = new JsonArray();
		while(iterator.hasNext())
		{
			JsonObject element = new JsonObject();
			Object[] objArray = (Object[])iterator.next();
			String userName = (String)objArray[0];
			String roleName = (String)objArray[1];
			String userState = (String)objArray[2];
			String userId = (String)objArray[3];
			element.add("TUserId", new JsonParser().parse(userId));
			element.add("TUserName", new JsonParser().parse(userName));
			element.add("TUserState", new JsonParser().parse(userState));
			element.add("roleName", new JsonParser().parse(roleName));
			
			elementArray.add(element);
			
		}
		obj.add("total", new JsonParser().parse(this.countNum()));
		obj.add("rows", new JsonParser().parse(elementArray.toString()));
   
		return obj.toString();
	}

	@Override
	public boolean addOrUpdate(TSysUser user) {
	
		return sysUserDao.addOrUpdate(user);
	}
	

	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}

	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}



	@Override
	public String countNum() {
			String hql = "select count(u) from TSysUser u";
		return String.valueOf(sysUserDao.countNum(hql));
	}



	
	
}
