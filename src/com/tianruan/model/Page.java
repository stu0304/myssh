package com.tianruan.model;

public class Page {

	// ��ǰ�ǵڼ�ҳ 
	private int currentPage;
	
	// ÿҳ��ʾ������
	private int pageSize;
	
	// �ӵڼ�ҳ��ʼ
	private int startPage;
	
	public Page(int currentPage,int pageSize)
	{
		this.setCurrentPage(currentPage);
		this.setPageSize(pageSize);
		//(currentPage-1)*pageSize
		this.setStartPage((this.getCurrentPage()-1)*this.getPageSize());
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	
	
}
