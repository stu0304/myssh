package com.tianruan.service;

public interface ITreeService {

	// 加载树形机构的json数据
	public String getTreeJSON(String pid,String userId);
	
	// 检查当前节点 是否有子节点
	public boolean checkChildNode(String nodeId);
}
