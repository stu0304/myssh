package com.tianruan.model;

public class Page {

	// 当前是第几页 
	private int currentPage;
	
	// 每页显示多少行
	private int pageSize;
	
	// 从第几页开始
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
