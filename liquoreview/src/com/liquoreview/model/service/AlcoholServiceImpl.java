package com.liquoreview.model.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Alcohol;
import com.liquoreview.model.repository.AlcoholDAO;

@Service("AlcoholService")
public class AlcoholServiceImpl implements AlcoholService{
	@Resource(name = "AlcoholDAO")
	private AlcoholDAO alcoholDAO;

	
	/**
	 * 술 정보 전체조회 + 페이징
	 * @author 이양원
	 * @date 2021. 03. 11  최초생성
	 * 			  개정이력  2021. 04. 18  
	 * @param 
	 * */
	@Override
	public List<Alcohol> alcoholLst(NewPager pager) {

		return alcoholDAO.alcoholLst(pager);
	}

	/**
	 * 상위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param 
	 * */
	@Override
	public List<Alcohol> topLst() {

		return alcoholDAO.topLst();
	}

	/**
	 * 하위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param param 
	 * */
	@Override
	public List<Alcohol> subLst(Map<String, Object> param) {

		return alcoholDAO.subLst(param);
	}

	/**
	 * 상위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param
	 * */
	@Override
	public int topCateReg(Alcohol vo) {

		return topCateReg(vo);
	}

	/**
	 * 하위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param
	 * */
	@Override
	public int subCateReg(Alcohol vo) {
		
		return subCateReg(vo);
	}

	/**
	 * 주류 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param param
	 * */
	@Override
	public int alcoholReg(Map<String, Object> param) {

		return alcoholDAO.alcoholReg(param);
	}

	/**
	 * 게시물 총 갯수
	 * @date 2021. 04. 18
	 * */
	@Override
	public int countTopCate() {
		
		return alcoholDAO.countTopCate();
	}

	/**
	 * 주류 정보 상세보기
	 * @author 이양원
	 * @date 2021. 05. 31  최초생성
	 * @param alcoholId
	 * */
	@Override
	public Alcohol alcoholLstIqrDtl(int alcoholId) {
		
		return alcoholDAO.alcoholLstIqrDtl(alcoholId);
	}
	
	

}
