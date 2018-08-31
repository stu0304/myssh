package com.tianrruan.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tianruan.model.TSysUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestGson {

	@Test
	public void test1()
	{
		TSysUser xiaoming = new TSysUser();
		xiaoming.setTUserName("zhangsan");
		xiaoming.setTUserId("001");
	//	xiaoming.setTSysPwd("111");
		
		TSysUser xiaohong = new TSysUser();
		xiaohong.setTUserName("kite");
		xiaohong.setTUserId("002");
	//	xiaohong.setTSysPwd("222");
		
		List userList = new ArrayList();
		userList.add(xiaoming);
		userList.add(xiaohong);
		
		Gson gson = new Gson(); 
		//System.out.println("gson.........."+gson.toJson(userList));
		
		JsonObject obj = new JsonObject();
		obj.add("total", new JsonParser().parse("10"));
		obj.add("rows", new JsonParser().parse(gson.toJson(userList)));
		
		JsonObject objProperty = new JsonObject();
		objProperty.addProperty("total", true);
		objProperty.addProperty("rows", gson.toJson(userList));
		
		System.out.println(objProperty.toString());
		
	}
	
	@Test
	public void test2()
	{
		TSysUser xiaoming = new TSysUser();
		xiaoming.setTUserName("zhangsan");
		xiaoming.setTUserId("001");
		//xiaoming.setTSysPwd("111");
		
		TSysUser xiaohong = new TSysUser();
		xiaohong.setTUserName("kite");
		xiaohong.setTUserId("002");
	//	xiaohong.setTSysPwd("222");
		
		List userList = new ArrayList();
		userList.add(xiaoming);
		userList.add(xiaohong);
		
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		array.add(JSONObject.fromObject(userList));
		
		obj.put("total", 11);
		obj.put("rows", array);
		
		System.out.println("JSON...."+obj.toString());
	}
	
}
