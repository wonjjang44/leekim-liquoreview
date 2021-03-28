package com.liquoreview.model.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Subcategory;
import com.liquoreview.model.domain.Topcategory;

@Repository("CategoryDAO")
public class MybatisCategoryDAO implements CategoryDAO{
	@Autowired
	private SqlSessionTemplate session;

	
	/**
	 * 상위 카테고리 목록 전체조회
	 * @author 이양원
	 * @date 2021. 03. 23  최초생성
	 * @param 
	 * */
	@Override
	public List<Topcategory> topCateLstIqr(NewPager pager) {

		//return session.selectList("topCateLstIqr");
		return session.selectList("topCateLstIqrPager", pager);
	}

	/**
	 * 하위 카테고리 목록 전체조회 + 추후 검색 기능 추가 예정
	 * @author 이양원
	 * @date 2021. 03. 24  최초생성
	 * @param 
	 * */
	@Override
	public List<Subcategory> subCateLstIqr() {

		return session.selectList("subCateLstIqr");
	}
	
	@Override
	public int countTopCate() {

		return session.selectOne("countTopCate");
	}
}
