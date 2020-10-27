package com.neuedu.crm.pub.page;

/**
 * 分页参数类
 * 
 */
public class PageParameter {
	public static final int DEFAULT_PAGE_SIZE = 10;

	private int pageSize;
	private int currentPage;
	private int prePage;
	private int nextPage;
	private int totalPage;
	private int totalCount;

	public PageParameter() {
		this.currentPage = 1;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}

	/**
	 * 
	 * @param currentPage
	 * @param pageSize
	 */
	public PageParameter(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getNextPage() {
		return this.nextPage;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getPrePage() {
		return this.prePage;
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
