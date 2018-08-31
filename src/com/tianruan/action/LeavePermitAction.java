package com.tianruan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.JsonObject;
import com.tianruan.model.Page;
import com.tianruan.model.TLeavePermit;
import com.tianruan.model.TPerson;
import com.tianruan.model.TWorkLog;
import com.tianruan.service.ILeavePermitService;
import com.tianruan.service.IPersonService;
import com.tianruan.service.IWorkLogService;
import com.tianruan.util.SplitUtil;

public class LeavePermitAction implements ServletRequestAware,ServletResponseAware{
	private IPersonService personService;
	private IWorkLogService workLogService;
	private ILeavePermitService leavePermitService;
	private TLeavePermit leavePermit;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void checkLeavePermit() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		leavePermit.setTStatus("已审核");
		leavePermit.setTAdminId("管理员");
		if(leavePermitService.addOrUpdate(leavePermit))
		{
			boolean result=false;
			String startTime=request.getParameter("tStartTime");
			String endTime=request.getParameter("tEndTime");
			
			String personName=request.getParameter("tPersonId");			
			TPerson person = personService.getPersonIdByPerson(personName);
			String personid=person.getTPersonId();	
			
			int tStartTime=SplitUtil.splitDay(startTime);
			int tEndTime=SplitUtil.splitDay(endTime);
			String allYMonth=SplitUtil.splitYMonth(startTime);
						
			for(int i=tStartTime;i<=tEndTime;i++){
				String iString=Integer.toString(i);
				List<TWorkLog> workLogList=workLogService.loadAllWorkLog(personid, allYMonth+iString);//查询这条记录   年月+日循环
				for(TWorkLog workLog : workLogList){
					workLog.setTWorkLate("2");
					workLog.setTWorkLess("2");
					workLog.setTWorkNone("2");
					result=workLogService.addOrUpdate(workLog);
				}
			}	
			if(result){
				this.myPrintWriter(response, "true");
			}else{
				this.myPrintWriter(response, "false");
			}
			
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	public void loadAllLeavePermitJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?10:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(leavePermitService.loadAllLeavePermitJSON(pager, searchName));
		out.flush();
	
	}

	public void deleteLeavePermit() throws IOException
	{
		String lpid = request.getParameter("lpid");
		TLeavePermit leavePermit=new TLeavePermit();
		//department.setTDeptId(deptid);
		leavePermit.setTLpId(lpid);
		JsonObject objProperty = new JsonObject();
		if(leavePermitService.deleteLeavePermit(leavePermit))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void editLeavePermit() throws IOException
	{
		if(leavePermitService.addOrUpdate(leavePermit))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	public void addLeavePermit() throws IOException
	{
		TPerson person=(TPerson) request.getSession().getAttribute("myPerson");
		leavePermit.setTLpId(UUID.randomUUID().toString());
		leavePermit.setTStatus("未审核");
		leavePermit.setTPersonId(person.getTPersonName());
		leavePermit.setTAdminId("无");
		if(leavePermitService.addOrUpdate(leavePermit))
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
		response=arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
	}
	public ILeavePermitService getLeavePermitService() {
		return leavePermitService;
	}
	public void setLeavePermitService(ILeavePermitService leavePermitService) {
		this.leavePermitService = leavePermitService;
	}
	public TLeavePermit getLeavePermit() {
		return leavePermit;
	}
	public void setLeavePermit(TLeavePermit leavePermit) {
		this.leavePermit = leavePermit;
	}
	public IWorkLogService getWorkLogService() {
		return workLogService;
	}
	public void setWorkLogService(IWorkLogService workLogService) {
		this.workLogService = workLogService;
	}
	public IPersonService getPersonService() {
		return personService;
	}
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}
	
}
