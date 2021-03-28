package com.liquoreview.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liquoreview.model.service.MailService;
import com.liquoreview.model.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	@Resource(name = "MailService")
	private MailService mailService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 로그인 페이지 이동
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		return "client/member/login";
	}
	
	/**
	 * 비밀번호 찾기 팝업창 이동
	 * @author 이양원
	 * @date 2021. 02. 06
	 * @param 
	 * */
	@RequestMapping(value = "/popup/fndPassPopView", method = RequestMethod.GET)
	public String getFindPassView() {
		
		return "client/member/popup/fndPassPopView";
	}
	
	/**
	 * 비밀번호 (이메일)로 찾기 페이지로 이동
	 * @author 이양원
	 * @date 2021. 02. 06
	 * @param
	 * */
	@RequestMapping(value = "/popup/fndPassPop", method = RequestMethod.GET)
	public String getFindPassCertify() {
		
		return "client/member/popup/fndPassPop";
	}
	
	/**
	 * 회원가입 페이지 이동
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String regist() {
		
		return "client/member/regist";
	}
	
	/**
	 * 로그아웃(세션끊기)
	 * @author 이양원
	 * @date 2021. 01. 30  최초생성
	 * 			  2021. 02. 13   개정
	 * 				 				  로그아웃시 기존 로그인 페이지에서 메인 페이지로 경로 변경 
	 * @param session
	 * */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	

}
