package com.liquoreview.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.PassSecurity;
import com.liquoreview.model.service.member.MemberService;

@Controller
public class AdminMainController {
	@Autowired
	MemberService memberService;
	@Autowired
	PassSecurity security;

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value="/admin")
	public ModelAndView adminGoLogin(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/main/index");
		return mav;
	}
}
