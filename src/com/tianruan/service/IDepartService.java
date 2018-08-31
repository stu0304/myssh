package com.tianruan.service;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.TDepartment;
import com.tianruan.model.TPerson;


public interface IDepartService {
	
	public String getAllDepartJSON(Page pager, String searchName);
	
	public boolean addOrUpdate(TDepartment department);
	
	public boolean deletedepart(TDepartment department);
	
	public String countNum();
	// ͳ��ÿ�����ŵ�����
	public String getDeptPersonNum();
	
	public String getDeptPersonNumColumn();

}
