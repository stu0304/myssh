package com.tianruan.service;

import java.util.List;

import com.tianruan.model.RRoleModule;
import com.tianruan.model.Page;
import com.tianruan.model.TModules;


public interface IModulesService {
	
	public String loadAllModulesJSON(Page pager, String searchName);
	
	public boolean addOrUpdate(TModules modules);
	
	public boolean deleteModules(TModules modules);
	
	public String countNum();

	public boolean addRoleModule(RRoleModule roleModule);

}
