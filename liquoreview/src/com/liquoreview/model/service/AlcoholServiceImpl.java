package com.liquoreview.model.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.model.domain.Alcohol;
import com.liquoreview.model.repository.AlcoholDAO;

@Service("AlcoholService")
public class AlcoholServiceImpl implements AlcoholService{
	@Resource(name = "AlcoholDAO")
	private AlcoholDAO alcoholDAO;

	
	/**
	 * 술 정보 전체조회
	 * @author 이양원
	 * @date 2021. 03. 11  최초생성
	 * @param 
	 * */
	@Override
	public List<Alcohol> alcoholLst() {

		return alcoholDAO.alcoholLst();
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
	
	

}
