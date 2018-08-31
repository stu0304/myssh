package com.tianruan.service;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.RUserRole;
import com.tianruan.model.TRole;
import com.tianruan.model.TSysUser;

public interface IRoleService {
	
	  public boolean addOrUpdateUserAndRole(RUserRole userRole);
	//分页
		public String loadAllRoleJSON(Page pager,String personName);
		
		//插入更新
		public boolean addOrUpdateRole(TRole role);
		
		//删除
		public boolean deleteRole(TRole role);
		
		//总页数
		public String countNum();
		
		//角色名称 下拉数据
		public String findUserRole();
		
		public String getRoleByUserId(TSysUser user);
		
		public boolean updateUserAndRole(RUserRole userRole);
}
