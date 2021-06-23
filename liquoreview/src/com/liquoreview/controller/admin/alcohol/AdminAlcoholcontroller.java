package com.liquoreview.controller.admin.alcohol;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.Pager;
import com.liquoreview.model.service.alcohol.AlcoholService;
import com.liquoreview.model.service.alcohol.SubcategoryService;
import com.liquoreview.model.service.alcohol.TopcategoryService;

@Controller
public class AdminAlcoholcontroller {
	
	@Autowired
	AlcoholService alcoholService;
	@Autowired
	TopcategoryService topcategoryService;
	@Autowired
	SubcategoryService subcategoryService;
	
	@Autowired
	Pager pager;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	// 주류 관리 페이지 이동
	@RequestMapping(value = "/admin/alcohols")
	public ModelAndView goAdminAlcohol(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/alcohol/alcohol-manage");
		return mav;
	}
}
