package com.tianruan.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianruan.model.Page;
import com.tianruan.model.RNoticePerson;
import com.tianruan.model.TNotice;


public class NoticeDao extends CommonDAO{
	
	public boolean addAllNotice(RNoticePerson noticePerson)
	{
		return super.addOrUpdate(noticePerson);
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	public List findJob() {
		String hql = "select n from TNotice n";
		return super.getObjectList(hql);

	}
	
	public List getAllNoticeList(Page pager,String searchName)
	{
        StringBuffer bufferSQL = new StringBuffer();
		
		bufferSQL.append("select n from TNotice n");
		
		if(null!=searchName&&!"".equals(searchName))
		{
			bufferSQL.append(" where n.TTitle like '%"+searchName+"%' ");
		}
		
		bufferSQL.append(" order by n.TTitle");
		
		return super.getObjectList(bufferSQL.toString(),pager);
	}
  
	public boolean addNotice(TNotice notice)
	{
		return super.addOrUpdate(notice);
	}
	
	public boolean deleteNotice(TNotice notice)
	{
		return super.deleteObj(notice);
		
	}
}

