package com.tianruan.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tianruan.model.Page;

public class CommonDAO extends HibernateDaoSupport{

	public int getTotalNum(String hql){
		List list = null;
		try {
			list = getHibernateTemplate().find(hql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(list.get(0)+"");
	}
	
	public List getObjectListSQL(String sql)
	{
		  List objList = super.getHibernateTemplate().executeFind(new HibernateCallback() {
			   
			  @Override
				public List doInHibernate(Session session)
				{
				    Query query = session.createSQLQuery(sql);
		
				    return query.list();
				}
			   
		});
		
		return objList;
	}
	
	public List getObjectListSQL(String sql,Page pager)
	{
		  List objList = super.getHibernateTemplate().executeFind(new HibernateCallback() {
			   
			  @Override
				public List doInHibernate(Session session)
				{
				    Query query = session.createSQLQuery(sql);
				    // currentPgae
				    query.setFirstResult(pager.getStartPage());
				    // pageSize
				    query.setMaxResults(pager.getPageSize());
				    return query.list();
				}
			   
		});
		
		// call back  don't call me, i'll call you.....  回调函数
		return objList;
	}
	// overload 重载  方法名相同，参数不同 1.参数个数 2.参数类型 3.参数顺序
	public List getObjectList(String hql,Page pager)
	{
		  List objList = super.getHibernateTemplate().executeFind(new HibernateCallback() {
			   
			  @Override
				public List doInHibernate(Session session)
				{
				    Query query = session.createQuery(hql);
				    // currentPgae
				    query.setFirstResult(pager.getStartPage());
				    // pageSize
				    query.setMaxResults(pager.getPageSize());
				    return query.list();
				}
			   
		});
		
		// call back  don't call me, i'll call you.....  回调函数
		return objList;
	}
	
	public List getObjectList(String hql)
	{
		  List objList = super.getHibernateTemplate().executeFind(new HibernateCallback() {
			   
			  @Override
				public List doInHibernate(Session session)
				{
				    Query query = session.createQuery(hql);
		
				    return query.list();
				}
			   
		});
		
		// call back  don't call me, i'll call you.....  回调函数
		return objList;
	}
	
	
	
	public boolean addOrUpdate(Object obj)
	{
		try {
			super.getHibernateTemplate().saveOrUpdate(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean addObject(Object entity)
	{
		try {
			super.getHibernateTemplate().save(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean updateObject(Object entity)
	{
		try {
			super.getHibernateTemplate().update(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean deleteObj(Object obj)
	{
		try {
			super.getHibernateTemplate().delete(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
