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
import com.tianruan.model.TDepartment;
import com.tianruan.model.TPerson;
import com.tianruan.service.IDepartService;
import com.tianruan.service.IPersonService;

public class DepartAction implements ServletRequestAware,ServletResponseAware{
	
	private IDepartService departService;
    private HttpServletRequest request;
	private HttpServletResponse response;
	private TDepartment department;
	
	public void loadAllDepartJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?3:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(departService.getAllDepartJSON(pager, searchName));
		out.flush();
	
	}

	public void deleteDepart() throws IOException
	{
		String Depratid = request.getParameter("Depratid");
		
		department.setTDeptId(Depratid);	
		JsonObject objProperty = new JsonObject();
		if(departService.deletedepart(department))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void editDepart() throws IOException
	{
		if(departService.addOrUpdate(department))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	public void addDepart() throws IOException
	{
	/*	person.setTPersonId(UUID.randomUUID().toString()); 
		person.setTJobId(UUID.randomUUID().toString());
		person.setTDepartmentId(UUID.randomUUID().toString());*/
		department.setTDeptId(UUID.randomUUID().toString());
		if(departService.addOrUpdate(department))
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

	public IDepartService getDepartService() {
		return departService;
	}

	public void setDepartService(IDepartService departService) {
		this.departService = departService;
	}

	public TDepartment getDepartment() {
		return department;
	}

	public void setDepartment(TDepartment department) {
		this.department = department;
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



}
