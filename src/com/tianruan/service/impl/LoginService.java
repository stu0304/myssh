package com.tianruan.service.impl;

import java.util.List;

import com.tianruan.dao.LoginDao;
import com.tianruan.model.TSysUser;
import com.tianruan.service.ILoginService;

public class LoginService implements ILoginService{

	private LoginDao loginDao;
	
	@Override
	public TSysUser login(TSysUser user) {
		 List userList = loginDao.login(user);
		 return  userList.size()==0?null:(TSysUser)userList.get(0);

	}

	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	
	

}