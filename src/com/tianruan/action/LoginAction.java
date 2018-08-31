package com.tianruan.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.tianruan.model.TPerson;
import com.tianruan.model.TSysUser;
import com.tianruan.service.ILoginService;
import com.tianruan.service.IPersonService;
import com.tianruan.service.IRoleService;

public class LoginAction implements ServletRequestAware,SessionAware{

	private HttpServletRequest request;
	private Map sessionMap;
	private ILoginService loginService;
	private IPersonService personService;
	private IRoleService roleService;
	private TSysUser user;
	
	public String login()
	{
		TSysUser myUser = loginService.login(user);
		if(myUser!=null)
		{
		/*	HttpSession mysession = request.getSession();
			mysession.setAttribute("userObj", myUser);*/
			TPerson myPerson = personService.getPersonByPersonId(myUser.getTPersonId());
			sessionMap.put("userObj", myUser);
			sessionMap.put("myPerson", myPerson);
			sessionMap.put("myRole", roleService.getRoleByUserId(myUser));
			
			request.setAttribute("mymsg", "µÇÂ¼³É¹¦");
			return "mymain";
		}
		request.setAttribute("mymsg", "µÇÂ¼Ê§°Ü");
		return "myresult";
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public TSysUser getUser() {
		return user;
	}

	public void setUser(TSysUser user) {
		this.user = user;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap = arg0;
		
	}
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	
	
}
