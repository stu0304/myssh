package com.tianruan.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianruan.model.TSysUser;

public class LoginDao extends CommonDAO{

	public List login(TSysUser user) {
		String hql = "select u from TSysUser u "
				+ "where u.TUserName='"+user.getTUserName()+"' "
						+ "and u.TUserPwd='"+user.getTUserPwd()+"'";
		
	    return  super.getObjectList(hql);
	
	}
	
}
