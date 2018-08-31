package com.tianruan.dao;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.RUserRole;
import com.tianruan.model.TRole;
import com.tianruan.model.TSysUser;

public class RoleDao extends CommonDAO{

	public List getRoleByUserId(TSysUser user) {
		String sql = "select r.t_role_name from t_sys_user u "
				+ "left join r_user_role ur on u.t_user_id=ur.t_user_id "
				+ "left join t_role r on ur.t_role_id=r.t_role_id "
				+ "where u.t_user_id='"+user.getTUserId()+"'";
        return  super.getObjectListSQL(sql);
	}
	
	public boolean addUseAndRole(RUserRole userRole) {
		boolean flag=false;
		try {
			flag= super.addObject(userRole);
			return flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateUseAndRole(RUserRole userRole) {
		boolean flag=false;
		try {
			flag= super.updateObject(userRole);
			return flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
	public List getAllRoleList(Page pager,String roleName) {
		
		StringBuffer bufferSQL = new StringBuffer();	
//,rm.t_rolemodule_id
		bufferSQL.append("select  u.t_role_id,u.t_role_name,m.t_module_id,IFNULL(m.t_module_name,'Œ¥∑÷≈‰') from t_role u LEFT JOIN r_role_module rm on u.t_role_id=rm.t_role_id left JOIN t_modules m on rm.t_module_id=m.t_module_id");
		
		if(null!=roleName && !"".equals(roleName))
		{
			bufferSQL.append(" where u.t_role_name like '%"+roleName+"%' ");
		}		
		bufferSQL.append(" order by u.t_role_name");
		
		return super.getObjectListSQL(bufferSQL.toString(),pager);

	}
	public List findRole() {
		String hql = "select r from TRole r";
		return super.getObjectList(hql);

	}

	public boolean addOrUpdate(TRole role) {
		boolean flag=false;
		try {
			flag= super.addOrUpdate(role);
			return flag;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public boolean delectRole(TRole role) {
		return super.deleteObj(role);
	}
}
