package com.smes.domain;

public class PageSetting {
	private static final int DEFAULT_MAX_PAGE = 20;
	private final int pageNumber;
	private final int maxPerPage;
	public PageSetting(int page) {
		this (page, DEFAULT_MAX_PAGE);
	}
	
	public PageSetting(int page, int maxPerPage) {
		this.pageNumber = page < 1 ? 0 : page;
		this.maxPerPage = maxPerPage;
	}

	public int getStartResult () {
		return pageNumber * maxPerPage; 
	}
	
	public int getMaxResult () {
		return maxPerPage;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
}
