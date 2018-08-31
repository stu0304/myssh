package com.tianruan.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.JsonObject;
import com.tianruan.model.Page;
import com.tianruan.model.TPerson;
import com.tianruan.model.TWorkLog;
import com.tianruan.model.TWorkRules;
import com.tianruan.service.IWorkLogService;
import com.tianruan.service.IWorkRulesService;
import com.tianruan.util.SplitUtil;

public class WorkLogAction implements ServletRequestAware,ServletResponseAware{
	
	private IWorkLogService workLogService;
	private IWorkRulesService workRulesService;
	private TWorkLog workLog;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void monthManage() throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Calendar cal = Calendar.getInstance();
		  int year = cal.get(Calendar.YEAR);
		  int month = cal.get(Calendar.MONTH)+1;
		  int day = cal.get(Calendar.DATE);
		  int remainderDay=0;
		  String allYMonth=Integer.toString(year)+Integer.toString(month);
	      switch(month)
	        {
	           case 1:
	           case 3:
	           case 5:
	           case 7:
	           case 8:
	           case 10:
	           case 12:remainderDay=31-day;	break;
	           case 4:
	           case 6:
	           case 9:
	           case 11:remainderDay=30-day;	break;
	           case 2: if((year%4==0 && (year%100)!=0) || year%400==0)
	        	   			remainderDay=29-day;
	                   else
	                    	remainderDay=28-day; 
	                        break;
	        }
	      	TPerson person=(TPerson) request.getSession().getAttribute("myPerson");
	      	String personid=person.getTPersonId();
			if(workLogService.addAllPersonAndLog(allYMonth,remainderDay,day,personid))
			{
				this.myPrintWriter(response, "true");
			}else{
				this.myPrintWriter(response, "false");
			}
	}
	public void signManage() throws IOException, ParseException
	{
		boolean workLogValue=false;
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String mysignTime = df.format(new Date());
		//获取当前的年月日
		Calendar cal = Calendar.getInstance();
		  int year = cal.get(Calendar.YEAR);
		  int month = cal.get(Calendar.MONTH)+1;
		  int day = cal.get(Calendar.DATE);
		String allYMDay=Integer.toString(year)+Integer.toString(month)+Integer.toString(day);
				
		TPerson person=(TPerson) request.getSession().getAttribute("myPerson");
      	String personid=person.getTPersonId();
		List<TWorkLog> workLogList=workLogService.loadAllWorkLog(personid, allYMDay);
		List<TWorkRules> workRulesList=workRulesService.loadAllWorkRules();
		for(TWorkRules workRules: workRulesList){
			//系统时间
			Integer mysign = SplitUtil.splitTime(mysignTime);
			//上班时间
			Integer workStartTime = SplitUtil.splitTime(workRules.getTStartTime());
			//下班时间
			Integer workEndTime = SplitUtil.splitTime(workRules.getTEndTime());
			//旷工/迟到时间
			Integer workAfterMinite = SplitUtil.splitTime(workRules.getTAfterMinute());
			
			for(TWorkLog workLog: workLogList){
				//上午打卡
				if(mysign<1200 && workLog.getTStartTime().equals("0")){
						//正常上班打卡	
					if (mysign < workStartTime) {		 	
						workLog.setTStartTime(mysignTime);
						workLogValue=workLogService.addOrUpdate(workLog);
						//迟到
					}else if (mysign>workStartTime && mysign<=workAfterMinite) {
						workLog.setTStartTime(mysignTime);
						workLog.setTWorkLate("1");
						workLogValue=workLogService.addOrUpdate(workLog);
						//旷工
					}else if(mysign>workAfterMinite && mysign<1200){
						workLog.setTStartTime(mysignTime);
						workLog.setTWorkNone("1");
						workLogValue=workLogService.addOrUpdate(workLog);
					}
				}else{						
						//正常下班打卡
					if(mysign > workEndTime){	
						workLog.setTEndTime(mysignTime);					
						workLogValue=workLogService.addOrUpdate(workLog);						
						//早退					
					}else if(mysign < workEndTime && mysign> workStartTime) {
						workLog.setTEndTime(mysignTime);
						workLog.setTWorkLess("1");
						workLogValue=workLogService.addOrUpdate(workLog);
					}			
 				}
			}
		}
		if(workLogValue)
		{
			this.myPrintWriter(response, "true");
		}else{
			this.myPrintWriter(response, "false");
		}
		
	}
	
	public void loadAllWorkLogJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String searchName = request.getParameter("searchName");
		TPerson person=(TPerson) request.getSession().getAttribute("myPerson");
      	String personid=person.getTPersonId();
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?10:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		
		out.print(workLogService.loadAllWorkLogJSON(pager, searchName,personid));
		out.flush();
	
	}
	
	public void loadAllWorkLogSumJSON() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?10:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		out.print(workLogService.loadAllWorkLogSumJSON(pager));
		out.flush();
	
	}
	
	public void deleteWorkLog() throws IOException
	{
		
		String logid = request.getParameter("logid");
		TWorkLog workLog=new TWorkLog();
		workLog.setTLogId(logid);
		JsonObject objProperty = new JsonObject();
		if(workLogService.deleteWorkLog(workLog))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void editWorkLog() throws IOException
	{
		if(workLogService.addOrUpdate(workLog))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	public void addWorkLog() throws IOException
	{
		TPerson person=(TPerson) request.getSession().getAttribute("myPerson");
		workLog.setTLogId(UUID.randomUUID().toString());
		if(workLogService.addOrUpdate(workLog))
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

	public IWorkLogService getWorkLogService() {
		return workLogService;
	}

	public void setWorkLogService(IWorkLogService workLogService) {
		this.workLogService = workLogService;
	}

	public TWorkLog getWorkLog() {
		return workLog;
	}

	public void setWorkLog(TWorkLog workLog) {
		this.workLog = workLog;
	}

	public IWorkRulesService getWorkRulesService() {
		return workRulesService;
	}

	public void setWorkRulesService(IWorkRulesService workRulesService) {
		this.workRulesService = workRulesService;
	}


}
