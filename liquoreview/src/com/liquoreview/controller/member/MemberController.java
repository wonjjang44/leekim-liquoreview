package com.liquoreview.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.PassSecurity;
import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;
import com.liquoreview.model.service.member.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	PassSecurity security;

	Logger logger = Logger.getLogger(this.getClass().getName());

	/*
	 * 페이지 이동
	 */

	// 회원가입 페이지 이동
	@RequestMapping(value = "/member/goRegist", method = RequestMethod.GET)
	public String goRegist() {
		logger.info("회원가입페이지로 이동============================");
		return "client/member/member-regist";
	}

	// 로그인 페이지로 이동
	@RequestMapping(value = "/member/goLogin", method = RequestMethod.GET)
	public ModelAndView goLogin(HttpServletRequest request) {
		logger.info("client login페이지로 이동============================");
		ModelAndView mav = new ModelAndView("client/member/member-login");
		return mav;
	}

	// logout
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		logger.info("회원 로그아웃");
		return "redirect:/";
	}
}
