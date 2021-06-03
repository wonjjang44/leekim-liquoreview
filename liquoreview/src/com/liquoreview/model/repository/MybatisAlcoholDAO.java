package com.liquoreview.model.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Alcohol;

@Repository("AlcoholDAO")
public class MybatisAlcoholDAO implements AlcoholDAO{
	@Autowired
	private SqlSessionTemplate session;

	/**
	 * 술 정보 전체조회 + 페이징
	 * @author 이양원
	 * @date 2021. 03. 11  최초생성
	 * 			  개정이력  2021. 04. 18  
	 * @param 
	 * */
	@Override
	public List<Alcohol> alcoholLst(NewPager pager) {
		
		return session.selectList("alcoholLstIqrPager", pager);
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

	/**
	 * 게시물 총 갯수
	 * @date 2021. 04. 18
	 * */
	@Override
	public int countTopCate() {
		
		return session.selectOne("alcCountTopCate");
	}

	/**
	 * 주류 정보 상세보기
	 * @author 이양원
	 * @date 2021. 05. 31  최초생성
	 * @param alcoholId
	 * */
	@Override
	public Alcohol alcoholLstIqrDtl(int alcoholId) {
		
		return session.selectOne("alcoholLstDtl", alcoholId);
	}

	
}
