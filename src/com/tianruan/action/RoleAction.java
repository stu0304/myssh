package com.tianruan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.JsonObject;
import com.tianruan.model.Page;
import com.tianruan.model.RUserRole;
import com.tianruan.model.TPerson;
import com.tianruan.model.TRole;
import com.tianruan.service.IRoleService;

public class RoleAction implements ServletRequestAware,ServletResponseAware{

	private HttpServletResponse response;
	private HttpServletRequest request;
	private IRoleService roleService;
	private TRole role;
	private RUserRole userRole;
	
	
	public void fixRole() throws IOException
	{
		String roleid = request.getParameter("roleid");
		String userid = request.getParameter("userid");
		RUserRole userRole = new RUserRole();
		
		userRole.setTRoleId(roleid);
		userRole.setTUserId(userid);
		
		if(roleService.updateUserAndRole(userRole))
		{
			this.myPrintWriter(response, "editok");
		}else{
			this.myPrintWriter(response, "editfail");
		}
	}
	
	public void grantRole() throws IOException
	{
	
		String roleid = request.getParameter("roleid");
		String userid = request.getParameter("userid");
		RUserRole userRole = new RUserRole();
		userRole.setTRoleId(roleid);
		userRole.setTUserId(userid);
		
		if(roleService.addOrUpdateUserAndRole(userRole))
		{
			this.myPrintWriter(response, "true");
		}else{
			this.myPrintWriter(response, "false");
		}
	}
	
	public void findRole() throws IOException
	{		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(roleService.findUserRole());
		out.flush();
	}
	
	public void loadAllRoleJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?3:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(roleService.loadAllRoleJSON(pager, searchName));
		out.flush();
	}
	
	public void addRole() throws IOException
	{
	   role.setTRoleId(UUID.randomUUID().toString());
		if(roleService.addOrUpdateRole(role))
		{
			this.myPrintWriter(response, "addok");
		}else{
			this.myPrintWriter(response, "addfail");
		}
	}
	
	public void editRole() throws IOException
	{
		if(roleService.addOrUpdateRole(role))
		{
			this.myPrintWriter(response, "editok");
		}else{
			this.myPrintWriter(response, "editfail");
		}
	}
	
	public void deletRole() throws IOException
	{
		String roleid=request.getParameter("roleid");
		TRole role=new TRole();
		role.setTRoleId(roleid);
		JsonObject objProperty = new JsonObject();

			if(roleService.deleteRole(role))
			{
				objProperty.addProperty("success", true);
			}else{
				objProperty.addProperty("fail", false);
			}
	
		this.myPrintWriter(response, objProperty.toString());
	}
	
	

	
	public void myPrintWriter(HttpServletResponse response,String json) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		out.println(json);
		out.flush();
	}
	
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response=arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=arg0;
		
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public TRole getRole() {
		return role;
	}

	public void setRole(TRole role) {
		this.role = role;
	}

	public RUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(RUserRole userRole) {
		this.userRole = userRole;
	}
	
}
