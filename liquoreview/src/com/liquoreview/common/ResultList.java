package com.liquoreview.common;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * db 조회결과를 담는 객체
 * @author 이양원
 * @date 21. 03. 24  최초생성
 * */
@Component
public class ResultList {
	private int total;				//전체 페이지 수 
	private int page;			//현재 페이지 수 
	private int records;			//전체 페이지 수 
	private List<?> rows;	// 행
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "ReadResultList [total="+total+", page="+page+ ", records=" +records+", rows="+rows +"]";
	}
	
}
