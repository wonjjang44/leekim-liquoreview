package com.liquoreview.model.repository;

import java.util.List;
import java.util.Map;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Alcohol;

public interface AlcoholDAO {
	/**
	 * 술 정보 전체조회 + 페이징
	 * @author 이양원
	 * @date 2021. 03. 11  최초생성
	 * 			  개정이력  2021. 04. 18  
	 * @param 
	 * */
	public List<Alcohol> alcoholLst(Criteria cri);
	
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
	
	/**
	 * 게시물 총 갯수
	 * @date 2021. 04. 18
	 *            개정이력 2021. 06. 12 함수명 변경
	 * */
	public int alcLstAllCnt();
	
	/**
	 * 주류 정보 상세보기
	 * @author 이양원
	 * @date 2021. 05. 31  최초생성
	 * @param alcoholId
	 * */
	public Alcohol alcoholLstIqrDtl(int alcoholId);
	
	/**
	 * 주류 정보 등록 직후 주류 이미지 데이터 등록
	 * @author 이양원
	 * @date 2021. 06. 04  최초생성
	 * @param param
	 * */
	public int alcImgReg(Map<String, Object> param);
	
	/**
	 * 주류 정보 삭제
	 * @author 이양원
	 * @date 2021. 06. 04  최초생성
	 * @param alcohol_id
	 * */
	public int alcoholDel(int alcohol_id);
	
	/**
	 * 주류 이미지 정보 삭제
	 * @author 이양원
	 * @date 2021. 06. 05  최초생성
	 * @param alcohol_id
	 * */
	public int alcoholImgDel(int alcohol_id);
}
