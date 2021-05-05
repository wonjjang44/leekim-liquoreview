package com.liquoreview.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class SearchCriteria extends Criteria{
	
	private String searchType;
	private String searchWord;

	public SearchCriteria() {
		super();
	}
	public SearchCriteria(HttpServletRequest request, int pageSize) {
		super(request, pageSize);
		// TODO Auto-generated constructor stub
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType 
					+ ", searchWord=" + searchWord 
					+ ", currentPage=" + super.getCurrentPage() 
					+ ", pageSize=" + super.getPageSize() 
					+ ", curPos=" + super.getCurPos()
					+"]";
	}
	
	
	
}
