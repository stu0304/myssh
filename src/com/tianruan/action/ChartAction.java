package com.tianruan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tianruan.service.IWorkLogService;
import com.tianruan.model.TDepartment;
import com.tianruan.service.IDepartService;

public class ChartAction implements ServletRequestAware,ServletResponseAware{
    private HttpServletRequest request;
	private HttpServletResponse response;
	private IDepartService departService;
	private IWorkLogService workLogService;	
	
	public void getLeaveColumn() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String json = workLogService.getworkLogColumn();
		out.println(json);
		out.flush();
	}
	public void getChartData() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String json = departService.getDeptPersonNum();
		/*System.out.println(json);*/
		out.println(json);
		out.flush();
	}
	
	public void getChartDataColumn() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String json = departService.getDeptPersonNumColumn();
		out.println(json);
		out.flush();
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
		
	}
	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}
	public IWorkLogService getWorkLogService() {
		return workLogService;
	}
	public void setWorkLogService(IWorkLogService workLogService) {
		this.workLogService = workLogService;
	}
	
	
}
