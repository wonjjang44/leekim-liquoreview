package com.liquoreview.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liquoreview.common.PassSecurity;
import com.liquoreview.model.domain.Member;
import com.liquoreview.model.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * 로그인 페이지 이동
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		return "/member/login";
	}
	
	/**
	 * 로그인 + SHA256을 통한 비밀번호 암호화
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param allRequestParams
	 * */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(Member allRequestParams, HttpSession session) {
		String msg  = "";
		
		// 비밀번호 암호화 (SHA256)
		String encryPassword = PassSecurity.encrypt(allRequestParams.getPass());
		allRequestParams.setPass(encryPassword);

		Member member = new Member();
		member = memberService.login(allRequestParams);
		
		if(member == null) {
			msg += "idFail";
		}else if(!allRequestParams.getPass().equals(member.getPass())) {
			msg += "pwFail";
		}else {
			session.setAttribute("loginSession", member);
			
			if(member.getAuth_id() == 1){
				msg += "admin";
			}else {
				msg += "user";
			}
		}
		
		return msg;
	}
	
	/**
	 * 아이디 중복체크
	 * @author 이양원
	 * @date 2021. 01. 30  최초생성
	 * @param userid
	 * */
	@RequestMapping(value = "/idChk", method = RequestMethod.GET)
	@ResponseBody
	public String loginChk(@RequestParam String userid) {
		
		return memberService.idChk(userid);
	}
	
	/**
	 * 회원가입 페이지 이동
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String regist() {
		
		return "/member/regist";
	}
	
	/**
	 * 회원가입
	 * @author 이양원
	 * @date 2021. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	@ResponseBody
	public int regist(@RequestBody Member allRequestParams) {
		
		return memberService.regist(allRequestParams);
	}
	
	/**
	 * 로그아웃(세션끊기)
	 * @author 이양원
	 * @date 2021. 01. 30  최초생성
	 * @param
	 * */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/member/login";
	}
	
}





