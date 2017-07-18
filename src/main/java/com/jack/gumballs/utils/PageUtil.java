package com.jack.gumballs.utils;

public class PageUtil {
	private int pageNo = 1; // 当前页码
	private int pageSize = 5; // 每页行数
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
