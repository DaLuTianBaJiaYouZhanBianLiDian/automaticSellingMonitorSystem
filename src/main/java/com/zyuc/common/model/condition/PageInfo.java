package com.zyuc.common.model.condition;

public class PageInfo {
	private Integer countTotal;
	private Integer countOfEveryPage = 10;
	private Integer currentPageNumber;
	private Integer pageTotal;
	private Integer startIndex = 0;
	
	public Integer getCountTotal() {
		return countTotal;
	}
	public void setCountTotal(Integer countTotal) {
		this.countTotal = countTotal;
	}
	public Integer getCountOfEveryPage() {
		return countOfEveryPage;
	}
	public void setCountOfEveryPage(Integer countOfEveryPage) {
		this.countOfEveryPage = countOfEveryPage;
	}
	public Integer getCurrentPageNumber() {
		return currentPageNumber;
	}
	public void setCurrentPageNumber(Integer currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	public Integer getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
}
