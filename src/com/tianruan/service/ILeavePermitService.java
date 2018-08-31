package com.tianruan.service;

import com.tianruan.model.Page;
import com.tianruan.model.TLeavePermit;

public interface ILeavePermitService {
	
	public String loadAllLeavePermitJSON(Page pager, String searchName);
	
	public boolean addOrUpdate(TLeavePermit leavePermit);
	
	public boolean deleteLeavePermit(TLeavePermit leavePermit);
	
	public String countNum();
}
