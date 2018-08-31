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
import com.tianruan.service.ITreeService;

public class TreeAction  implements ServletRequestAware,ServletResponseAware{


	private HttpServletRequest request;
	private HttpServletResponse response;
	private ITreeService treeService;
	
	
	public void loadTreeJSON() throws IOException
	{
		// 根节点  字节点   根子节点    递归 ： 自己调用自己
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 如果有 100个节点 
		
		// tree 每次点击  都会传来一个 节点id 
		String parentId = request.getParameter("id")==null?"-1":request.getParameter("id");
		TSysUser myUser = (TSysUser)request.getSession().getAttribute("userObj");
		out.print(treeService.getTreeJSON(parentId,myUser.getTUserId()));
		out.flush();
	
	}


	public ITreeService getTreeService() {
		return treeService;
	}


	public void setTreeService(ITreeService treeService) {
		this.treeService = treeService;
	}


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response=arg0;
		
	}


	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request=arg0;
		
	}
	
	
	
	
	
}
