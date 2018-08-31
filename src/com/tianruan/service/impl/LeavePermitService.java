package com.tianruan.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.tianruan.dao.LeavePermitDao;
import com.tianruan.model.Page;
import com.tianruan.model.TLeavePermit;
import com.tianruan.service.ILeavePermitService;

public class LeavePermitService implements ILeavePermitService{
	
	private LeavePermitDao leavePermitDao;
	@Override
	public String loadAllLeavePermitJSON(Page pager, String searchName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		obj.add("rows", new JsonParser().parse(gson.toJson(leavePermitDao.getAllLeavePermitList(pager,searchName))));

		return obj.toString();
	}

	@Override
	public boolean addOrUpdate(TLeavePermit leavePermit) {	
		return leavePermitDao.addOrUpdate(leavePermit);
	}
	
	@Override
	public boolean deleteLeavePermit(TLeavePermit leavePermit) {
		return leavePermitDao.deleteObj(leavePermit);
	}

	@Override
	public String countNum() {
		String hql = "select count(u) from TLeavePermit u";
		return String.valueOf(leavePermitDao.countNum(hql));
	}

	public LeavePermitDao getLeavePermitDao() {
		return leavePermitDao;
	}

	public void setLeavePermitDao(LeavePermitDao leavePermitDao) {
		this.leavePermitDao = leavePermitDao;
	}

}
