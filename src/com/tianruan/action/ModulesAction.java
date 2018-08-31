package com.tianruan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.JsonObject;
import com.tianruan.model.RRoleModule;
import com.tianruan.model.Page;
import com.tianruan.model.TModules;
import com.tianruan.service.IModulesService;
import com.tianruan.service.IPersonService;

public class ModulesAction  implements ServletRequestAware,ServletResponseAware{

	private IModulesService modulesService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private TModules modules;
	
	public void grantModule() throws IOException
	{
		boolean roleModuleValue= false;
		RRoleModule roleModule=new RRoleModule();		
		String modulesid = request.getParameter("modulesid");
		String roleid = request.getParameter("roleid");
		String[] str = modulesid.split(",");
		for(int i=0; i < str.length;i++){ 
		    roleModule.setTModuleId(str[i]);
		    roleModule.setTRoleId(roleid);
		    roleModuleValue = modulesService.addRoleModule(roleModule);
		}
		if(roleModuleValue){
			this.myPrintWriter(response, "addOk");
		}else{
			this.myPrintWriter(response, "addfalse");
		}
	}
	public void loadAllModulesJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?3:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(modulesService.loadAllModulesJSON(pager, searchName));
		out.flush();
	
	}
	
	public void deleteModules() throws IOException
	{
		String TModueId = request.getParameter("modulesid");
		TModules modules = new TModules();
		modules.setTModuleId(TModueId);
		JsonObject objProperty = new JsonObject();
		if(modulesService.deleteModules(modules))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void editModules() throws IOException
	{
		if(modulesService.addOrUpdate(modules))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	
	public void addModules() throws IOException
	{
	
		modules.setTModuleId(UUID.randomUUID().toString());
//		modules.setTModuleName(request.getParameter("TModuleName"));
//		modules.setTModulePid(request.getParameter("TModulePid"));
//		modules.setTModuleUrl(request.getParameter("TModuleUrl"));
//		
		if(modulesService.addOrUpdate(modules))
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
	

	public IModulesService getModulesService() {
		return modulesService;
	}

	public void setModulesService(IModulesService modulesService) {
		this.modulesService = modulesService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public TModules getModules() {
		return modules;
	}

	public void setModules(TModules modules) {
		this.modules = modules;
	}

	
	
}
