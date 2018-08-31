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
import com.tianruan.model.TWorkRules;
import com.tianruan.service.IWorkRulesService;

public class WorkRulesAction implements ServletRequestAware,ServletResponseAware{
	
	private IWorkRulesService workRulesService;
	private TWorkRules workRules;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void loadAllWorkRulesJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?10:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(workRulesService.loadAllWorkRulesJSON(pager, searchName));
		out.flush();
	
	}

	public void deleteWorkRules() throws IOException
	{
		String ruleid = request.getParameter("ruleid");
		TWorkRules workRules=new TWorkRules();
		workRules.setTRuleId(ruleid);
		JsonObject objProperty = new JsonObject();
		if(workRulesService.deleteWorkRules(workRules))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void editWorkRules() throws IOException
	{
		if(workRulesService.addOrUpdate(workRules))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	public void addWorkRules() throws IOException
	{
		workRules.setTRuleId(UUID.randomUUID().toString());
		if(workRulesService.addOrUpdate(workRules))
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

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		response=arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	public IWorkRulesService getWorkRulesService() {
		return workRulesService;
	}
	public void setWorkRulesService(IWorkRulesService workRulesService) {
		this.workRulesService = workRulesService;
	}
	public TWorkRules getWorkRules() {
		return workRules;
	}
	public void setWorkRules(TWorkRules workRules) {
		this.workRules = workRules;
	}

}
