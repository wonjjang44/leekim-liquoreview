package com.liquoreview.controller.admin.review;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.model.service.review.ReviewService;

@Controller
public class AdminReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value="/admin/review", method = RequestMethod.GET)
	public ModelAndView goAdminReview(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/review/review-manage");
		return mav;
	}
}
