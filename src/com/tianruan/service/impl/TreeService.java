package com.tianruan.service.impl;

import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.dao.TreeDao;
import com.tianruan.model.TModules;
import com.tianruan.service.ITreeService;


public class TreeService implements ITreeService {

	private TreeDao treeDao;
	@Override
	public String getTreeJSON(String pid,String userId) {
		
		List treeList = treeDao.loadTreeList(pid,userId);
		Iterator iterator = treeList.iterator();
		JsonArray elementArray = new JsonArray();
		
		while(iterator.hasNext())
		{
			JsonObject element = new JsonObject();
			Object[] objArray = (Object[])iterator.next();
			String mid = (String)objArray[0];
			String mname = (String)objArray[1];
			String mpid = (String)objArray[2];
			String murl = (String)objArray[3];
			element.add("id", new JsonParser().parse(mid));
			element.add("text", new JsonParser().parse(mname));
			element.add("mpid", new JsonParser().parse(mpid));
			element.add("url", new JsonParser().parse(murl));
			
			if(checkChildNode(mid))
			{        // µ›πÈ≤È—Ø
				 // obj.add("children", new JsonParser().parse(this.getTreeJSON(m.getTModuleId())));
				element.add("state", new JsonParser().parse("closed"));
			}
			
			elementArray.add(element);
		}
		return elementArray.toString();
	}

	

	public TreeDao getTreeDao() {
		return treeDao;
	}

	public void setTreeDao(TreeDao treeDao) {
		this.treeDao = treeDao;
	}

	@Override
	public boolean checkChildNode(String nodeId) {
		
		return treeDao.checkChildNode(nodeId);
	}

	
}
