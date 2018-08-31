package com.tianruan.service.impl;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.PersonDao;
import com.tianruan.model.Page;
import com.tianruan.model.TPerson;
import com.tianruan.service.IPersonService;

public class PersonService implements IPersonService{
	
	private PersonDao personDao;
	
	@Override
	public TPerson getPersonIdByPerson(String personName) {
		List personList = personDao.getPersonIdByPerson(personName);
		 if(personList.size()>0)
		 {
			 return (TPerson)personList.get(0);
		 }
		 return null;
	}
	@Override
	public String loadAllPersonJSON(Page pager, String searchName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		obj.add("rows", new JsonParser().parse(gson.toJson(personDao.getAllSysUserList(pager,searchName))));

		return obj.toString();
	}

	@Override
	public boolean addOrUpdate(TPerson person) {	
			return personDao.addOrUpdate(person);
	}
	
	@Override
	public boolean deletePerson(TPerson person) {
		return personDao.deleteObj(person);
	}

	@Override
	public String countNum() {
		String hql = "select count(u) from TPerson u";
		return String.valueOf(personDao.countNum(hql));
	}
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public TPerson getPersonByPersonId(String personId) {
		List personList = personDao.getPersonByPersonId(personId);
		 if(personList.size()>0)
		 {
			 return (TPerson)personList.get(0);
		 }
		 return null;
	}




	
}
