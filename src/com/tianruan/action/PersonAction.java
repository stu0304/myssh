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
import com.tianruan.model.TPerson;
import com.tianruan.service.IPersonService;

public class PersonAction implements ServletRequestAware,ServletResponseAware{
	
	private IPersonService personService;
	private TPerson person;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void loadAllPersonJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?3:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(personService.loadAllPersonJSON(pager, searchName));
		out.flush();
	
	}

	public void deletePerson() throws IOException
	{
		String personid = request.getParameter("personid");
		TPerson person=new TPerson();
		person.setTPersonId(personid);
		JsonObject objProperty = new JsonObject();
		if(personService.deletePerson(person))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void editPerson() throws IOException
	{
		if(personService.addOrUpdate(person))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	public void addPerson() throws IOException
	{
		person.setTPersonId(UUID.randomUUID().toString()); 
		person.setTJobId(UUID.randomUUID().toString());
		person.setTDepartmentId(UUID.randomUUID().toString());
		if(personService.addOrUpdate(person))
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

	public IPersonService getPersonService() {
		return personService;
	}

	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	public TPerson getPerson() {
		return person;
	}

	public void setPerson(TPerson person) {
		this.person = person;
	}

}
