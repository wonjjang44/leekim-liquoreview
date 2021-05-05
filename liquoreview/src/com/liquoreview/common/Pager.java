package com.liquoreview.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Pager {
	
	private int totalRecord;
	private int totalPage;
	private int blockSize;
	private int firstPage;
	private int lastPage;
	private int num;
	
	private Criteria criteria;
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
		logger.info("criteria set되는 시점에 criteria.currentPage확인 : "+this.criteria.getCurrentPage());
		logger.info("criteria set되는 시점에 criteria.pageSize확인 : "+this.criteria.getPageSize());
		logger.info("criteria set되는 시점에 criteria.curPos확인 : "+this.criteria.getCurPos());
	}
	
	public void init(int totalRecord) {
		logger.info("pager.init 호출");
		logger.info("전달받은 totalRecord 확인 : "+totalRecord);
		this.totalRecord = totalRecord;
		this.blockSize = 10;

		this.totalPage = (int)(Math.ceil((double)totalRecord/this.criteria.getPageSize()));
		this.firstPage = this.criteria.getCurrentPage() - (this.criteria.getCurrentPage()- 1) % blockSize;
		this.lastPage = this.firstPage + (this.blockSize -1);
		
		logger.info("pager.num 계산 전 curPos확인 : "+this.criteria.getCurPos());
		this.num = totalRecord - this.criteria.getCurPos();
		logger.info("계산된 pager.num 확인 : "+this.num);
	}
	
	public int getTotalRecord() {
		return totalRecord;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public Criteria getCriteria() {
		return criteria;
	}

	@Override
	public String toString() {
		return "Pager [logger=" + logger + ", totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", blockSize="
				+ blockSize + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", num=" + num + ", criteria="
				+ criteria + "]";
	}
	
}
