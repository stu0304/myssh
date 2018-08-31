package com.tianruan.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianruan.model.Page;
import com.tianruan.model.TSysUser;

public class SysUserDao extends CommonDAO{

	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
	/**
	 * 
	 * @param hql
	 * @param pager
	 * @return
	 */
	public List getAllSysUserList(Page pager,String searchName)
	{
		// limit "+pager.getStartPage()+", "+pager.getPageSize()+"
         //  string 和 stringbuffer 区别   效率 差太多
		// 如果是查询功能 
		StringBuffer bufferSQL = new StringBuffer();
		
		bufferSQL.append("select u.t_user_name,IFNULL(r.t_role_name,'无角色') ,u.t_user_state, u.t_user_id  "
				+ " from t_sys_user u "
				+ "LEFT JOIN r_user_role ur on u.t_user_id=ur.t_user_id "
				+ "left JOIN t_role r on ur.t_role_id=r.t_role_id");
		
		if(null!=searchName&&!"".equals(searchName))
		{
			bufferSQL.append(" where u.t_user_name like '%"+searchName+"%'");
		}
		
		
		return super.getObjectListSQL(bufferSQL.toString(),pager);
	}
	
	public boolean addOrUpdate(TSysUser user)
	{
		return super.addOrUpdate(user);
	}
	
	public boolean deleteUser(TSysUser user)
	{
		return super.deleteObj(user);
	}
	
	
	
}
