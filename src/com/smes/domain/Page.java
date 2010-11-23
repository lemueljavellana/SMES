package com.smes.domain;

import java.util.Collection;

public class Page<T> {
	private final PageSetting pageSetting;
	private final Collection<T> data;
	private final int totalRecords;
	private final int dataSize;
	private final int currentPage;
	private final int nextPage;
	private final int lastPage;
	private final int prevPage;
	public Page(PageSetting pageSetting, Collection<T> data, int totalRecords) {
		this.pageSetting = pageSetting;
		this.totalRecords = totalRecords;
		this.data = data;
		this.dataSize = data.size();
		this.currentPage = pageSetting.getPageNumber() + 1;
		this.nextPage = currentPage + 1;
		this.prevPage = currentPage - 1;
		double lstPageT =  (double)totalRecords / (double)pageSetting.getMaxResult();
		this.lastPage = (int) (lstPageT % (int)lstPageT != 0 ? lstPageT + 1 : lstPageT); 
	}

	public PageSetting getPageSetting() {
		return pageSetting;
	}

	public Collection<T> getData() {
		return data;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public int getDataSize() {
		return dataSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getPrevPage() {
		return prevPage;
	}
	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", data=" + data
				+ ", dataSize=" + dataSize + ", lastPage=" + lastPage
				+ ", nextPage=" + nextPage + ", pageSetting=" + pageSetting
				+ ", prevPage=" + prevPage + ", totalRecords=" + totalRecords
				+ "]";
	}
}
