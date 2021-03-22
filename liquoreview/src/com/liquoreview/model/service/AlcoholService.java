package com.liquoreview.model.service;

import java.util.List;
import java.util.Map;

import com.liquoreview.model.domain.Alcohol;

public interface AlcoholService {
	/**
	 * 술 정보 전체조회
	 * @author 이양원
	 * @date 2021. 03. 11  최초생성
	 * @param 
	 * */
	public List<Alcohol> alcoholLst();
	
	/**
	 * 상위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param 
	 * */
	public List<Alcohol> topLst();
	
	/**
	 * 하위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param param 
	 * */
	public List<Alcohol> subLst(Map<String, Object> param);
}
