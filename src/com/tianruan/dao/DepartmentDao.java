package com.tianruan.dao;

import java.util.List;


import com.tianruan.model.TPerson;
import com.tianruan.model.Page;
import com.tianruan.model.TDepartment;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DepartmentDao extends CommonDAO{
	
	public List getDeptPersonNum()
	{
		String sql = "SELECT d.t_dept_name,COUNT(p.t_person_id) t_bianzhi from t_department d LEFT JOIN t_person p on d.t_dept_id=p.t_department_id GROUP BY d.t_dept_name ";
		
		return super.getObjectListSQL(sql);
	}
	
	public List getAllSysUserList(Page pager,String searchName)
	{
		StringBuffer bufferSQL = new StringBuffer();		
		bufferSQL.append("select d from TDepartment d ");	
		if(null!=searchName&&!"".equals(searchName))
		{
			bufferSQL.append(" where d.TDeptName like '%"+searchName+"%' ");
		}		
		bufferSQL.append(" order by d.TDeptName");
		
		return super.getObjectList(bufferSQL.toString(),pager);
	}
	public boolean addOrUpdate(TDepartment department){
		return super.addOrUpdate(department);
	}
	
	public boolean deleteUser(TDepartment department){
		return super.deleteObj(department);
	}
	
	public int countNum(String hql)
	{
		return super.getTotalNum(hql);
	}
	
}
