package com.tianruan.service;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.RUserRole;
import com.tianruan.model.TRole;
import com.tianruan.model.TSysUser;

public interface IRoleService {
	
	  public boolean addOrUpdateUserAndRole(RUserRole userRole);
	//��ҳ
		public String loadAllRoleJSON(Page pager,String personName);
		
		//�������
		public boolean addOrUpdateRole(TRole role);
		
		//ɾ��
		public boolean deleteRole(TRole role);
		
		//��ҳ��
		public String countNum();
		
		//��ɫ���� ��������
		public String findUserRole();
		
		public String getRoleByUserId(TSysUser user);
		
		public boolean updateUserAndRole(RUserRole userRole);
}
