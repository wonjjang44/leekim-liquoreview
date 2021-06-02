package com.liquoreview.controller.admin.alcohol;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.model.service.alcohol.SubcategoryService;
import com.liquoreview.model.service.alcohol.TopcategoryService;

@Controller
public class AdminCategoryController {
	
	@Autowired
	TopcategoryService topcategoryService;
	@Autowired
	SubcategoryService subcategoryService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	// 카테고리관리 페이지 이동
	@RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
	public ModelAndView goAdminCategory(HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView("admin/alcohol/category/cate-manage");
		return mav;
	}
}
