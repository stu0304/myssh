package com.tianruan.service;

import com.tianruan.model.Page;
import com.tianruan.model.RNoticePerson;
import com.tianruan.model.TNotice;

public interface INoticeService {
	public String loadAllNoticeJSON(Page pager,String searchName);
	public boolean addNotice(TNotice notice);
	public boolean deleteNotice(TNotice notice);
	public String countNum();
	
	public boolean addAllNotice(String noticeId);
}
