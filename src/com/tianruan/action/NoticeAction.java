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
import com.tianruan.model.TNotice;
import com.tianruan.model.TPerson;
import com.tianruan.service.INoticeService;
import com.tianruan.util.DateUtil;

public class NoticeAction  implements ServletRequestAware,ServletResponseAware{
	
	private INoticeService noticeService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private TNotice notice;
	private Page pager;
	
	// 审核通知下发
	public void checkNotice() throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String noticeID = request.getParameter("noticeID");
		
		
		if(noticeService.addAllNotice(noticeID))
		{
			this.myPrintWriter(response, "true");
		}
		this.myPrintWriter(response, "false");
		
	}

	public void loadAllNoticeJSON() throws IOException{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String searchName = request.getParameter("searchName");
		
		int startPage = request.getParameter("page")==null?1:Integer.valueOf(request.getParameter("page"));
		int pageSize =  request.getParameter("rows")==null?3:Integer.valueOf(request.getParameter("rows"));
		Page pager = new Page(startPage,pageSize);
		out.print(noticeService.loadAllNoticeJSON(pager, searchName));
		out.flush();
     }

	public void editNotice() throws IOException
	{
		
		if(noticeService.addNotice(notice))
		{
			this.myPrintWriter(response, "editOk");
		}else{
			this.myPrintWriter(response, "myerror");
		}
	}
	public void addNotice()throws IOException
	{ 
	    notice.setTNoticeId(UUID.randomUUID().toString());
	    TPerson person = (TPerson)request.getSession().getAttribute("myPerson");
	    notice.setTAdminId(person.getTPersonId());
	    notice.setTAddDate(DateUtil.getNowTime());
	    notice.setTStatus("未审核");
	    notice.setTVisitNum("0");
	    
		if(noticeService.addNotice(notice))
		{
			
			this.myPrintWriter(response, "okla");
		}else{
			this.myPrintWriter(response, "myerror");
		}
		
	
	}
	public void deleteNotice() throws IOException
	{
		String noticceID = request.getParameter("noticeid");
	    //person.setTUserId(personId);
	   //person.setTPersonId(personId);
		notice.setTNoticeId(noticceID);
		JsonObject objProperty = new JsonObject();
		if(noticeService.deleteNotice(notice))
		{
			objProperty.addProperty("success", true);
		}else{
			objProperty.addProperty("fail", false);
		}
		this.myPrintWriter(response, objProperty.toString());
		
	}
	
	public void myPrintWriter(HttpServletResponse response,String str) throws IOException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(str);
		out.flush();
	}

	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		response = arg0;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public TNotice getNotice() {
		return notice;
	}

	public void setNotice(TNotice notice) {
		this.notice = notice;
	}
	
	
	

}

