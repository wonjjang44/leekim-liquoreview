package com.liquoreview.controller.admin;

import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.model.domain.Member;
import com.liquoreview.model.service.MailService;
import com.liquoreview.model.service.MemberService;

@RestController
@RequestMapping(value = "/member")
public class RestMemberController {
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	@Resource(name = "MailService")
	private MailService mailService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	/**
	 * 로그인 + SHA256을 통한 비밀번호 암호화
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param allRequestParams, request
	 * */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(@RequestBody Member allRequestParams, HttpServletRequest request) {
		
		return memberService.login(allRequestParams, request);
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
	 * 비밀번호 (이메일)로 찾기
	 * @author 이양원
	 * @date 2021. 02. 08
	 * @param param
	 * */
	@RequestMapping(value = "/popup/fndPassPop", method = RequestMethod.POST)
	@ResponseBody
	public Boolean postFindPassCertify(@RequestBody Member param, HttpSession session) {
		int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        session.setAttribute("joinCode", joinCode);
 
        String subject = "회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");


		return mailService.mailSend(subject, sb.toString(), "아이디@gmail.com", param.getEMAIL(), null);
	}
	
 }
