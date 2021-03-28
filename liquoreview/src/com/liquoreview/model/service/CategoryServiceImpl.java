package com.liquoreview.model.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Subcategory;
import com.liquoreview.model.domain.Topcategory;
import com.liquoreview.model.repository.CategoryDAO;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{
	@Resource(name = "CategoryDAO")
	private CategoryDAO categoryDAO;

	
	/**
	 * 상위 카테고리 목록 전체조회
	 * @author 이양원
	 * @date 2021. 03. 23  최초생성
	 * @param 
	 * */
	@Override
	public List<Topcategory> topCateLstIqr(NewPager pager) {

		return categoryDAO.topCateLstIqr(pager);
	}

	/**
	 * 하위 카테고리 목록 전체조회 + 추후 검색 기능 추가 예정
	 * @author 이양원
	 * @date 2021. 03. 24  최초생성
	 * @param 
	 * */
	@Override
	public List<Subcategory> subCateLstIqr() {

		return categoryDAO.subCateLstIqr();
	}

	@Override
	public int countTopCate() {

		return categoryDAO.countTopCate();
	}
}
