package com.tianruan.service;

public interface ITreeService {

	// �������λ�����json����
	public String getTreeJSON(String pid,String userId);
	
	// ��鵱ǰ�ڵ� �Ƿ����ӽڵ�
	public boolean checkChildNode(String nodeId);
}
