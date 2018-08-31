package com.tianruan.dao;

import java.util.List;
import com.tianruan.model.Page;
import com.tianruan.model.TLeavePermit;

public class LeavePermitDao extends CommonDAO{
	
	public List getAllLeavePermitList(Page pager,String searchName)
	{
		StringBuffer bufferSQL = new StringBuffer();		
		bufferSQL.append("select u from TLeavePermit u ");	
		if(null!=searchName && !"".equals(searchName))
		{
			bufferSQL.append(" where u.TStartTime like '%"+searchName+"%' ");
		}		
		bufferSQL.append(" order by u.TStartTime");		
		return super.getObjectList(bufferSQL.toString(),pager);
	}
	public boolean addOrUpdate(TLeavePermit leavePermit){
		return super.addOrUpdate(leavePermit);
	}
	
	public boolean deleteLeavePermit(TLeavePermit leavePermit){
		return super.deleteObj(leavePermit);
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
}
