package com.liquoreview.controller.admin.member;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.model.service.member.MemberService;

@Controller
public class AdminMemberController {
	
	@Autowired
	MemberService memberService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/*
	 * 페이지 이동 
	 */
	
	// 회원정보관리 페이지 이동
	@RequestMapping(value="/admin/member", method = RequestMethod.GET)
	public ModelAndView adminGetMemberList(HttpServletRequest request) {
		logger.info("회원정보관리 페이지 이동=========================");
		ModelAndView mav = new ModelAndView("admin/member/member-table");
		return mav;
	}
}
