package com.tianruan.service.impl;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.model.RRoleModule;
import com.tianruan.dao.ModulesDao;
import com.tianruan.model.Page;
import com.tianruan.model.TModules;
import com.tianruan.service.IModulesService;

public class ModulesService implements IModulesService{
	
	private ModulesDao modulesDao;
	@Override
	public boolean addRoleModule(RRoleModule roleModule) {
		
		return modulesDao.addRoleModule(roleModule);
	}
	public String loadAllModulesJSON(Page pager, String searchName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		obj.add("rows", new JsonParser().parse(gson.toJson(modulesDao.getAllModulesList(pager,searchName))));

		return obj.toString();
	}

	public String countNum() {
		 
			String hql = "select count(m) from TModules m";
			return String.valueOf(modulesDao.countNum(hql));
		}
	

	@Override
	public boolean addOrUpdate(TModules modules) {	

		return modulesDao.addOrUpdate(modules);

	}

	public ModulesDao getModulesDao() {
		return modulesDao;
	}

	public void setModulesDao(ModulesDao modulesDao) {
		this.modulesDao = modulesDao;
	}

	@Override
	public boolean deleteModules(TModules modules) {
		
		return modulesDao.deleteModules(modules);
	}

	
}
