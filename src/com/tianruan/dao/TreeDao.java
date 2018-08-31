package com.tianruan.dao;

import java.util.List;

public class TreeDao extends CommonDAO{
	public List loadTreeList(String pid,String userId) {
		/**
		 * select u.t_user_name,m.t_module_name from t_sys_user u LEFT JOIN r_user_role ur on u.t_user_id=ur.t_user_id
			LEFT JOIN t_role r on ur.t_role_id=r.t_role_id 
			LEFT JOIN r_role_module rm on r.t_role_id=rm.t_role_id 
			LEFT JOIN t_modules m on rm.t_module_id=m.t_module_id
			where m.t_module_pid='4' and u.t_user_id='d51f6c5d-e2f8-4700-9689-7bb9b8f93701'
		 */
	    String sql = "select m.* from t_sys_user u "
	    		+ "LEFT JOIN r_user_role ur on u.t_user_id=ur.t_user_id "
	    		+ "LEFT JOIN t_role r on ur.t_role_id=r.t_role_id  "
	    		+ "LEFT JOIN r_role_module rm on r.t_role_id=rm.t_role_id  "
	    		+ "LEFT JOIN t_modules m on rm.t_module_id=m.t_module_id "
	    		+ "where m.t_module_pid='"+pid+"' and u.t_user_id='"+userId+"'";
		return super.getObjectListSQL(sql);
	}


	public boolean checkChildNode(String nodeId) {
		   String hql = "select m from TModules m where m.TModulePid='"+nodeId+"'";
		   return super.getObjectList(hql).size()!=0?true:false;
	
	}
}
