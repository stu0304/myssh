package com.tianruan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.JsonObject;
import com.tianruan.model.Page;
import com.tianruan.model.TSysUser;
import com.tianruan.service.ISysUserService;

public class SysUserAction  implements ServletRequestAware,ServletResponseAware{

	private ISysUserService sysUserService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private TSysUser user;
	private Page pager;
	
	public void loadAllSysUserJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?3:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(sysUserService.loadAllSysUserJSON(pager,searchName));
		out.flush();
	
	}
	
	public void deleteSysUser() throws IOException
	{
		String userId = request.getParameter("userid");
		TSysUser user = new TSysUser();
		user.setTUserId(userId);
		JsonObject objProperty = new JsonObject();
		if(sysUserService.deleteSysUser(user))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void editSysUser() throws IOException
	{
		
		if(sysUserService.addOrUpdate(user))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	
	public void addSysUser() throws IOException
	{
		/*TSysUser user = new TSysUser();
		user.setTUserId(UUID.randomUUID().toString());
		user.setTPersonId(UUID.randomUUID().toString());
		user.setTUserName(request.getParameter("TUserName"));
		user.setTUserPwd(request.getParameter("TUserPwd"));  
		;*/
		user.setTUserState("0");
		if(sysUserService.addOrUpdate(user))
		{
			this.myPrintWriter(response, "okla");
		}else{
			this.myPrintWriter(response, "myerror");
		}
		
	
	}
	
	public void myPrintWriter(HttpServletResponse response,String json) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

	public ISysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(ISysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}

	public TSysUser getUser() {
		return user;
	}

	public void setUser(TSysUser user) {
		this.user = user;
	}

	
	
	
}
