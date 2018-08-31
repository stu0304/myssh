package com.tianruan.service;


import com.tianruan.model.Page;
import com.tianruan.model.TSysUser;

public interface ISysUserService {

	public String loadAllSysUserJSON(Page pager,String searchName);
	
	public boolean addOrUpdate(TSysUser user);
	
	public boolean deleteSysUser(TSysUser user);

	public String countNum();
}
