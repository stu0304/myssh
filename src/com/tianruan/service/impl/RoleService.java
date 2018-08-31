package com.tianruan.service.impl;

import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.RoleDao;
import com.tianruan.model.Page;
import com.tianruan.model.RUserRole;
import com.tianruan.model.TRole;
import com.tianruan.model.TSysUser;
import com.tianruan.service.IRoleService;

public class RoleService implements IRoleService {
	private RoleDao roleDao;
	
	@Override
	public boolean updateUserAndRole(RUserRole userRole) {
		return roleDao.updateObject(userRole);
	}
	@Override
	public boolean addOrUpdateUserAndRole(RUserRole userRole)
	{
		  return roleDao.addUseAndRole(userRole);

	}
	
	public String findUserRole()
	{
		Gson gson = new Gson();
		return gson.toJson(roleDao.findRole());
	}

	@Override
	public String loadAllRoleJSON(Page pager, String personName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		List list=roleDao.getAllRoleList(pager, personName);		
		JsonArray jsonArray=new JsonArray();
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Object[] array=(Object[]) iterator.next();
			
			JsonObject jObject=new JsonObject();
			jObject.add("TRoleId", new JsonParser().parse(String.valueOf(array[0])));
			jObject.add("TRoleName", new JsonParser().parse(String.valueOf(array[1])));
			jObject.add("TModuleId", new JsonParser().parse(String.valueOf(array[2])));			
			jObject.add("TModuleName", new JsonParser().parse(String.valueOf(array[3])));
			//jObject.add("TRolemoduleId", new JsonParser().parse(String.valueOf(array[4])));
			
			jsonArray.add(new JsonParser().parse(jObject.toString()));
		}
		
		obj.add("rows", new JsonParser().parse(gson.toJson(jsonArray)));
		return obj.toString();
	}

	@Override
	public boolean addOrUpdateRole(TRole role) {
		// TODO Auto-generated method stub
		return roleDao.addOrUpdate(role);
	}

	@Override
	public boolean deleteRole(TRole role) {
		// TODO Auto-generated method stub
		return roleDao.delectRole(role);
	}

	@Override
	public String countNum() {
		String hql="select count(r) from TRole r";
		return String.valueOf(roleDao.countNum(hql));
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	@Override
	public String getRoleByUserId(TSysUser user) {
		
		List roleList = roleDao.getRoleByUserId(user);
		if(roleList.size()>0)
		{
			return (String)roleList.get(0);
		}
		
		return "";
	}


}
