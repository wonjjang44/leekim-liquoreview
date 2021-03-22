package com.liquoreview.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.model.domain.Alcohol;
import com.liquoreview.model.service.AlcoholService;

@Controller
@RequestMapping(value = "/admin/alcohol")
public class AlcoholController {
	@Resource(name = "AlcoholService")
	private AlcoholService alcoholService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	/**
	 * 술 정보 등록 페이지 이동
	 * @author 이양원
	 * @date 2021. 03. 10  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/alcoholReg", method = RequestMethod.GET)
	public String alcoholReg() {
		
		return "/admin/alcohol/alcohol-regist";
	}
	
	/**
	 * 상위 카테고리 페이지 이동
	 * @author 이양원
	 * @date 2021. 03. 10  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/topcategoryLst", method = RequestMethod.GET)
	public String topcategoryLst() {
		
		return "/admin/category/category-top-table";
	}
	
	/**
	 * 하위 카테고리 페이지 이동
	 * @author 이양원
	 * @date 2021. 03. 10  최초생성
	 * @param
	 * */
	@RequestMapping(value = "subcategoryLst", method = RequestMethod.GET)
	public String subcategoryLst() {
		
		return "/admin/category/category-sub-table";
	}
}
