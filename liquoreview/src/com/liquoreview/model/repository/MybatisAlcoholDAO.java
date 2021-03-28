package com.liquoreview.model.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.Alcohol;

@Repository("AlcoholDAO")
public class MybatisAlcoholDAO implements AlcoholDAO{
	@Autowired
	private SqlSessionTemplate session;

	/**
	 * 술 정보 전체조회
	 * @author 이양원
	 * @date 2021. 03. 11  최초생성
	 * @param 
	 * */
	@Override
	public List<Alcohol> alcoholLst() {

		return session.selectList("alcoholLst");
	}
	
	/**
	 * 상위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param 
	 * */
	@Override
	public List<Alcohol> topLst() {

		return session.selectList("topLst");
	}

	/**
	 * 하위 카테고리 전체조회
	 * @author 이양원
	 * @date 2021. 03. 17  최초생성
	 * @param param 
	 * */
	@Override
	public List<Alcohol> subLst(Map<String, Object> param) {
		
		return session.selectList("subLst", param);
	}

	/**
	 * 상위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param vo
	 * */
	@Override
	public int topCateReg(Alcohol vo) {

		return session.insert("topCateReg", vo);
	}

	/**
	 * 하위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param vo
	 * */
	@Override
	public int subCateReg(Alcohol vo) {

		return session.insert("subCateReg", vo);
	}

	/**
	 * 주류 등록
	 * @author 이양원
	 * @date 2021. 03. 22  최초생성
	 * @param param
	 * */
	@Override
	public int alcoholReg(Map<String, Object> param) {

		return session.insert("alcoholReg", param);
	}



	
}
