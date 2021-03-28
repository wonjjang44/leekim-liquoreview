package com.liquoreview.model.repository;

import java.util.List;
import java.util.Map;

import com.liquoreview.model.domain.Alcohol;

public interface AlcoholDAO {
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
	
	/**
	 * 상위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param vo
	 * */
	public int topCateReg(Alcohol vo);
	
	/**
	 * 하위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param vo
	 * */
	public int subCateReg(Alcohol vo);
	
	/**
	 * 주류 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param vo
	 * */
	public int alcoholReg(Map<String, Object> param);
}
