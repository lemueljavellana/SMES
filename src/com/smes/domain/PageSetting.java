package com.smes.domain;

public class PageSetting {
	private static final int MAXMUM_ITEM_PAGE = 20;
	private final int pageNumber;
	
	public PageSetting(int page) {
		this.pageNumber = page < 1 ? 0 : page;
	}

	public int getStartResult () {
		return pageNumber * MAXMUM_ITEM_PAGE; 
	}
	
	public int getMaxResult () {
		return MAXMUM_ITEM_PAGE;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
}
