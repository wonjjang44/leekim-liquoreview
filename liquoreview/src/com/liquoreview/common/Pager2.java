package com.liquoreview.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Pager2 {
	private int currentPage = 1;
	private int totalRecord;
	private int pageSize;
	private int totalPage;
	private int blockSize;
	private int firstPage;
	private int lastPage;
	private int curPos;
	private int num;
	
	public void init(HttpServletRequest request, int totalRecord, int pageSize, int blockSize) {
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.blockSize = blockSize;

		totalPage = (int)(Math.ceil((double)totalRecord/pageSize));
		firstPage = currentPage - (currentPage - 1) % blockSize;
		lastPage = firstPage + (blockSize -1);
		
		curPos = (currentPage -1) * pageSize;
		num = totalRecord - curPos;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getCurPos() {
		return curPos;
	}
	public void setCurPos(int curPos) {
		this.curPos = curPos;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
