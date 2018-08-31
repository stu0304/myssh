package com.tianruan.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.PersonDao;
import com.tianruan.dao.WorkLogDao;
import com.tianruan.model.Page;
import com.tianruan.model.TPerson;
import com.tianruan.model.TWorkLog;
import com.tianruan.model.TWorkRules;
import com.tianruan.service.IPersonService;
import com.tianruan.service.IWorkLogService;
import com.tianruan.service.IWorkRulesService;
import com.tianruan.util.DateUtil;
import com.tianruan.util.SplitUtil;

public class WorkLogService implements IWorkLogService,ServletRequestAware{
	private HttpServletRequest request;
	private WorkLogDao workLogDao;
	private PersonDao personDao;
	private IWorkRulesService workRulesService;
	private IPersonService personService;
	@Override
	public String loadAllWorkLogSumJSON(Page pager) {
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		List list=workLogDao.getworkLogColumn();	
		JsonArray jsonArray=new JsonArray();
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Object[] array=(Object[]) iterator.next();
			JsonObject jObject=new JsonObject();
			TPerson person = personService.getPersonByPersonId(String.valueOf(array[0]));
			jObject.add("tpersonid", new JsonParser().parse(person.getTPersonName()));
			jObject.add("tworklate", new JsonParser().parse(String.valueOf(array[2])));
			jObject.add("tworkless", new JsonParser().parse(String.valueOf(array[3])));
			jObject.add("tworknone", new JsonParser().parse(String.valueOf(array[4])));
			jObject.add("tworkleave", new JsonParser().parse(String.valueOf(array[5])));
			
			jsonArray.add(new JsonParser().parse(jObject.toString()));
		}
		
		obj.add("rows", new JsonParser().parse(gson.toJson(jsonArray)));
		return obj.toString();
	}
	//柱状图
	@Override
	public String getworkLogColumn() {
		List workLogList = workLogDao.getworkLogColumn();
		Iterator iterator = workLogList.iterator();
		JsonArray array = new JsonArray();
		while(iterator.hasNext())
		{
			JsonObject element = new JsonObject();
			Object[] objArray = (Object[])iterator.next();
			
			TPerson person = personService.getPersonByPersonId((String)objArray[0]);
			element.addProperty("deptName", person.getTPersonName());
	
			double rateValue=(Double.valueOf(String.valueOf(objArray[1]))-Double.valueOf(String.valueOf(objArray[2]))-Double.valueOf(String.valueOf(objArray[3]))-Double.valueOf(String.valueOf(objArray[4]))-Double.valueOf(String.valueOf(objArray[5])))/Double.valueOf(String.valueOf(objArray[1]));
			element.addProperty("y", rateValue);
			array.add(element);
		}
	    System.out.println( array.toString());
		return array.toString();
	}
	@Override
	public boolean addAllPersonAndLog(String allYMonth, int remainderDay,int day,String personid) {
		List<TPerson> personList = personDao.getAllPersonList();
		boolean result = false;
	    if(personList.size()>0)
	    {
	    	for(int i=0;i<personList.size();i++)
			{
	    		if(i>0){
	    			day=day-remainderDay-1;//加上今天
	    		}   
	    		TWorkLog workLog=new TWorkLog();
	    		for(int j=0;j<remainderDay+1;j++){	    
	    			String allYMDay=allYMonth+Integer.toString(day);
	    			int YMDay=Integer.valueOf(allYMDay);
	    			workLog.setTLogId(UUID.randomUUID().toString());
	    			workLog.setTLogDate(Integer.toString(YMDay));
    				workLog.setTPersonId(personList.get(i).getTPersonId());
    				workLog.setTWorkLate("0");
    				workLog.setTWorkLess("0");
    				workLog.setTWorkNone("0");
    				workLog.setTStartTime("0");
    				workLog.setTEndTime("0");	
	    			workLogDao.addOrUpdate(workLog);
	    			day++;
	    		}	    		
			}
	    	result=true;
	  }
	
		return result;
	}
	@Override
	public List loadAllWorkLog(String personid,String allYMDay) {
		return workLogDao.getAllWorkLogList(personid,allYMDay);
	}
	@Override
	public String loadAllWorkLogJSON(Page pager, String searchName,String personid) {
		
		Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse(this.countNum()));
		List list=workLogDao.getAllWorkLogList(pager,searchName,personid);	
		JsonArray jsonArray=new JsonArray();
		Iterator iterator=list.iterator();
		while(iterator.hasNext()){
			Object[] array=(Object[]) iterator.next();
			JsonObject jObject=new JsonObject();
			jObject.add("TLogId", new JsonParser().parse(String.valueOf(array[0])));
			jObject.add("TPersonId", new JsonParser().parse(String.valueOf(array[1])));
			jObject.add("TLogDate", new JsonParser().parse(String.valueOf(array[2])));
			
			String tStartTime=SplitUtil.splitMyTime(String.valueOf(array[3]));//09:50无法存入
			String tEndTime=SplitUtil.splitMyTime(String.valueOf(array[4]));
			String tWorkLate=SplitUtil.splitMyTime(String.valueOf(array[5]));
			String tWorkLess=SplitUtil.splitMyTime(String.valueOf(array[6]));
			String tWorkNone=SplitUtil.splitMyTime(String.valueOf(array[7]));
			
			jObject.add("TStartTime", new JsonParser().parse(tStartTime));			
			jObject.add("TEndTime", new JsonParser().parse(tEndTime));			
			jObject.add("TWorkLate", new JsonParser().parse(tWorkLate));			
			jObject.add("TWorkLess", new JsonParser().parse(tWorkLess));			
			jObject.add("TWorkNone", new JsonParser().parse(tWorkNone));
			
			jsonArray.add(new JsonParser().parse(jObject.toString()));
		}
		
		obj.add("rows", new JsonParser().parse(gson.toJson(jsonArray)));
		System.out.println(obj.toString());
		return obj.toString();
	}
	@Override
	public boolean Update(TWorkLog workLog) {
		// TODO Auto-generated method stub
		return workLogDao.Update(workLog);
	}
	@Override
	public boolean addOrUpdate(TWorkLog workLog) {	
			return workLogDao.addOrUpdate(workLog);
	}
	
	@Override
	public boolean deleteWorkLog(TWorkLog workLog) {
		return workLogDao.deleteObj(workLog);
	}

	@Override
	public String countNum() {
		String hql = "select count(u) from TWorkLog u";
		return String.valueOf(workLogDao.countNum(hql));
	}
	public WorkLogDao getWorkLogDao() {
		return workLogDao;
	}
	public void setWorkLogDao(WorkLogDao workLogDao) {
		this.workLogDao = workLogDao;
	}
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request=arg0;
		
	}
	public IWorkRulesService getWorkRulesService() {
		return workRulesService;
	}
	public void setWorkRulesService(IWorkRulesService workRulesService) {
		this.workRulesService = workRulesService;
	}
	public IPersonService getPersonService() {
		return personService;
	}
	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	
}
