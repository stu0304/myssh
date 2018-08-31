package com.tianruan.dao;

import java.util.List;

import com.tianruan.model.Page;
import com.tianruan.model.RNoticePerson;
import com.tianruan.model.TWorkLog;

public class WorkLogDao extends CommonDAO{
	
	public List getworkLogColumn()
	{
		String sql = "select t_person_id,COUNT(t_log_date),"
				+ "sum(case when t_work_late='1' then 1 else 0 end) ³Ùµ½,"
				+ "sum(case when t_work_less='1' then 1 else 0 end) ÔçÍË,"
				+ "sum(case when t_work_none='1' then 1 else 0 end)+ sum(case when t_start_time='0' and t_end_time='0' then 1 else 0 end)- sum(case when t_work_none='2' then 1 else 0 end) ¿õ¹¤,"
				+ "sum(case when t_work_none='2' then 1 else 0 end) Çë¼Ù  "
				+ "from t_work_log "
				+ "group by t_person_id";		
		return super.getObjectListSQL(sql);
	}
	public List getAllWorkLogList(String personid,String allYMDay)
	{
		String hql = "select u from TWorkLog u where u.TPersonId='"+personid+"' and u.TLogDate='"+allYMDay+"'";
		return super.getObjectList(hql);
	}
	public List getAllWorkLogList(Page pager,String searchName,String personid)
	{
		StringBuffer bufferSQL = new StringBuffer();		
		bufferSQL.append("select * from t_work_log WHERE t_person_id='"+personid+"'");	
		if(null!=searchName && !"".equals(searchName))
		{
			bufferSQL.append(" and t_start_time like '%"+searchName+"%' ");
		}		
		bufferSQL.append(" order by t_log_date");		
		return super.getObjectListSQL(bufferSQL.toString(),pager);
	}
	
	public boolean Update(TWorkLog workLog){
		return super.updateObject(workLog);
	}
	public boolean addOrUpdate(TWorkLog workLog){
		return super.addOrUpdate(workLog);
	}
	
	public boolean deleteWorkLog(TWorkLog workLog){
		return super.deleteObj(workLog);
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
}
