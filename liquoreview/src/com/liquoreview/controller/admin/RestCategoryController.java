package com.liquoreview.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.NewPager;
import com.liquoreview.model.domain.Subcategory;
import com.liquoreview.model.domain.Topcategory;
import com.liquoreview.model.service.CategoryService;

@RestController
@RequestMapping("/admin/category")
public class RestCategoryController {
	@Resource(name = "CategoryService")
	private CategoryService categoryService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	/**
	 * 상위 카테고리 목록 전체조회 + 추후 검색 기능 추가 예정
	 * @author 이양원
	 * @date 2021. 03. 23  최초생성
	 * @param pager
	 * */
	@RequestMapping(value = "topCateLstIqr", method = RequestMethod.GET)
	public ModelAndView topCateLstIqr(NewPager pager, 
			@RequestParam(value = "nowPage", required=false, defaultValue = "1") String nowPage, 
			@RequestParam(value = "cntPerPage", required = false, defaultValue = "5") String cntPerPage){
		ModelAndView mav = new ModelAndView();
		
		int total = categoryService.countTopCate();

		pager = new NewPager(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<Topcategory> categoryVal = new ArrayList<Topcategory>();
		categoryVal = categoryService.topCateLstIqr(pager);
		
		mav.addObject("pager", pager);
		mav.addObject("topCategoryVal", categoryVal);
		mav.setViewName("/admin/category/category-top-table");
		
		return mav;
	}
	
	/**
	 * 하위 카테고리 목록 전체조회 + 추후 검색 기능 추가 예정
	 * @author 이양원
	 * @date 2021. 03. 24  최초생성
	 * @param 
	 * */
	@RequestMapping(value = "subCateLstIqr", method = RequestMethod.GET)
	public ModelAndView subCateLstIqr() {
		ModelAndView mav = new ModelAndView();
		List<Subcategory> categoryVal = new ArrayList<Subcategory>();
		categoryVal = categoryService.subCateLstIqr();
		
		mav.addObject("subCategoryVal", categoryVal);
		mav.setViewName("/admin/category/category-sub-table");
		
		return mav;
	}
	/**
	 * 상위 카테고리 등록
	 * @author 이양원
	 * @date 2021. 03. 23  최초생성
	 * @param 
	 * */
	@RequestMapping(value = "topCateReg", method = RequestMethod.POST)
	public int topCateReg(@RequestBody Topcategory vo) {
		
		
		return 0;
	}
}
