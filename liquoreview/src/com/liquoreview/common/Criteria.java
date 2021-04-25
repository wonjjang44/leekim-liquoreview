package com.liquoreview.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Criteria {
	private int currentPage;
	private int pageSize;
	private int curPos;
	
	/*
	public Criteria() {
		this.currentPage = 1;
		this.pageSize = 10;
		this.curPos = (this.currentPage -1) * this.pageSize;
	}
	*/
	public Criteria() {}
	
	public Criteria(HttpServletRequest request, int pageSize) {
		if (request.getParameter("currentPage") != null) {
			this.currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} else {
			this.currentPage = 1;
		}
		this.pageSize = pageSize;
		this.curPos = (this.currentPage - 1)*this.pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		/*
		 * if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
		 * 
		 * */
		if (currentPage <= 0) {
			this.currentPage = 1;
			return;
		}
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <=0 || pageSize > 100) {
			this.pageSize = 10;
			return;
		}
		this.pageSize = pageSize;
	}
	
	public int getCurPos() {
		return curPos;
	}
	
	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}
}
