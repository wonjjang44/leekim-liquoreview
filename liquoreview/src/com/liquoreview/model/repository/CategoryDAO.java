package com.liquoreview.model.repository;

import java.util.List;

import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Subcategory;
import com.liquoreview.model.domain.Topcategory;

public interface CategoryDAO {
	/**
	 * 상위 카테고리 목록 전체조회
	 * @author 이양원
	 * @date 2021. 03. 23  최초생성
	 * @param 
	 * */
	public List<Topcategory> topCateLstIqr(NewPager pager);
	
	/**
	 * 게시물 총 갯수
	 * 
	 * */
	public int countTopCate();
	
	/**
	 * 하위 카테고리 목록 전체조회 + 추후 검색 기능 추가 예정
	 * @author 이양원
	 * @date 2021. 03. 24  최초생성
	 * @param 
	 * */
	public List<Subcategory> subCateLstIqr();
}
