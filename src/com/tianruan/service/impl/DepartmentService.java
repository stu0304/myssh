package com.tianruan.service.impl;

import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.DepartmentDao;

import com.tianruan.model.Page;
import com.tianruan.model.TDepartment;
import com.tianruan.service.IDepartService;


public class DepartmentService implements IDepartService{
	
	private DepartmentDao departdao;//get set

	@Override
	public String getAllDepartJSON(Page pager, String searchName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		obj.add("rows", new JsonParser().parse(gson.toJson(departdao.getAllSysUserList(pager, searchName))));

		return obj.toString();
	}



	@Override
	public boolean addOrUpdate(TDepartment department) {
		// TODO Auto-generated method stub
		return departdao.addOrUpdate(department);
	}

	@Override
	public boolean deletedepart(TDepartment department) {
		// TODO Auto-generated method stub
		return departdao.deleteObj(department);
	}

	@Override
	public String countNum() {
		String hql = "select count(d) from TDepartment d";
		return String.valueOf(departdao.countNum(hql));
	}



	public DepartmentDao getDepartdao() {
		return departdao;
	}



	public void setDepartdao(DepartmentDao departdao) {
		this.departdao = departdao;
	}



	@Override
	public String getDeptPersonNum() {
	    List deptPersonList = departdao.getDeptPersonNum();
		Iterator iterator = deptPersonList.iterator();
		JsonArray array = new JsonArray();
		while(iterator.hasNext())
		{
			JsonObject element = new JsonObject();
			Object[] objArray = (Object[])iterator.next();
			element.addProperty("name", "ÈËÊý");
			element.addProperty("deptName", (String)objArray[0]);
		/*	element.addProperty("color", "red");*/
			element.addProperty("y", Double.valueOf(String.valueOf(objArray[1])));
			array.add(element);
		}
	    System.out.println( array.toString());
		return array.toString();
	}



	@Override
	public String getDeptPersonNumColumn() {
	    List deptPersonList = departdao.getDeptPersonNum();

			return new Gson().toJson(deptPersonList);
	}



	
}
