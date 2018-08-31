package com.tianruan.service.impl;

import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.NoticeDao;
import com.tianruan.dao.PersonDao;
import com.tianruan.model.Page;
import com.tianruan.model.RNoticePerson;
import com.tianruan.model.TNotice;
import com.tianruan.model.TPerson;
import com.tianruan.service.INoticeService;
import com.tianruan.service.IPersonService;
import com.tianruan.util.DateUtil;
import com.tianruan.util.StringUitl;


public class NoticeService implements INoticeService {
     private NoticeDao noticeDao;
 	 private IPersonService personService;
 	 private PersonDao personDao;

	@Override
	public String loadAllNoticeJSON(Page pager, String searchName) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		
		List<TNotice> noticeList = noticeDao.getAllNoticeList(pager, searchName);
		JsonArray elementArray = new JsonArray();
		for(TNotice notice : noticeList)
		{
			JsonObject element = new JsonObject();
			element.add("TNoticeId", new JsonParser().parse(notice.getTNoticeId()));
			element.add("TTitle", new JsonParser().parse(notice.getTTitle()));
			element.add("TContent", new JsonParser().parse(notice.getTContent()));
			TPerson person = personService.getPersonByPersonId(notice.getTAdminId());
			element.add("TAdminId", new JsonParser().parse(person.getTPersonName()));
			element.add("TAddDate", new JsonParser().parse(DateUtil.getWebTime(notice.getTAddDate(),"yyyy-MM-dd")));
			element.add("TStatus", new JsonParser().parse(notice.getTStatus()));
			element.add("TVisitNum", new JsonParser().parse(notice.getTVisitNum()));
			elementArray.add(element);
		}
		
		obj.add("rows", new JsonParser().parse(elementArray.toString()));

		return obj.toString();
	}

	@Override
	public String countNum() {
		String hql = "select count(n) from TNotice n";
		return String.valueOf(noticeDao.countNum(hql));
	}
	
	@Override
	public boolean addNotice(TNotice notice) {
		// TODO Auto-generated method stub
		return noticeDao.addOrUpdate(notice);
	}

	@Override
	public boolean deleteNotice(TNotice notice) {
		// TODO Auto-generated method stub
		return noticeDao.deleteObj(notice);
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	@Override
	public boolean addAllNotice(String noticeId) {
		
		List<TPerson> personList = personDao.getAllPersonList();
		boolean result = false;
	    if(personList.size()>0)
	    {
	    	for(int i=0;i<personList.size();i++)
			{
				RNoticePerson noticePerson = new RNoticePerson();
				noticePerson.setTNpId(StringUitl.getRandomId());
				noticePerson.setTNoticeId(noticeId);
				noticePerson.setTPersonId(personList.get(i).getTPersonId());
				noticePerson.setTRead("0");
				noticeDao.addAllNotice(noticePerson);
			}
		
	    	result=true;
	    }
	
		return result;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	
	
	


			
}
