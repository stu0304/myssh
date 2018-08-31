package com.tianruan.service;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.TPerson;


public interface IPersonService {
	
	public String loadAllPersonJSON(Page pager, String searchName);
	
	public boolean addOrUpdate(TPerson person);
	
	public boolean deletePerson(TPerson person);
	
	public String countNum();
	
	public TPerson getPersonByPersonId(String personId);
	
	public TPerson getPersonIdByPerson(String personName);

}
