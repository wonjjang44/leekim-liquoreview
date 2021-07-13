package com.liquoreview.controller.admin.alcohol;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.Pager;
import com.liquoreview.model.domain.alcohol.Alcohol;
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
	@RequestMapping(value = "/admin/alcohols", method = RequestMethod.GET)
	public ModelAndView goAdminAlcohol(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/alcohol/alcohol-manage");
		return mav;
	}
	
	//주류관리 상세페이지 이동
	@RequestMapping(value = "/admin/alcohol/{alcohol_id}/page", method = RequestMethod.GET)
	public ModelAndView goAdminAlcoholDetail(HttpServletRequest request, @PathVariable("alcohol_id") int alcohol_id, @RequestParam("currentPage") int currentPage) {
		logger.info("전달받은 alcohol_id 확인 : "+alcohol_id);
		
		//alcohol detail
		Alcohol alcoholDetail = alcoholService.select(alcohol_id);
		
		ModelAndView mav = new ModelAndView("admin/alcohol/alcohol-detail");
		mav.addObject("alcoholDetail", alcoholDetail);
		mav.addObject("currentPage", currentPage);
		return mav;
	}
}
