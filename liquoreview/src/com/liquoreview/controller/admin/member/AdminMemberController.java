package com.liquoreview.controller.admin.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liquoreview.common.BirthSetter;
import com.liquoreview.model.domain.admin.Auth;
import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;
import com.liquoreview.model.service.member.AuthService;
import com.liquoreview.model.service.member.MemberService;

@Controller
public class AdminMemberController {

	@Autowired
	MemberService memberService;
	@Autowired
	AuthService authService;

	Logger logger = Logger.getLogger(this.getClass().getName());

	/*
	 * 페이지 이동
	 */

	// 회원정보관리 페이지 이동
	@RequestMapping(value = "/admin/member", method = RequestMethod.GET)
	public ModelAndView goAdminMember(HttpServletRequest request) {
		logger.info("회원정보관리 페이지 이동=========================");
		ModelAndView mav = new ModelAndView("admin/member/member-manage");
		return mav;
	}

	// 회원 상세페이지 이동
	@RequestMapping(value = "/admin/member/{member_id}", method = RequestMethod.GET)
	public ModelAndView goAdminMemberDetail(HttpServletRequest request, @PathVariable("member_id") int member_id) {
		logger.info("전달받은 member_id 확인 : " + member_id);
		// 회원 상세정보
		Member memberDetail = memberService.select(member_id);
		MemberPw detailMemberPw = memberService.selectByMemId(member_id);
		
		ModelAndView mav = new ModelAndView("admin/member/member-detail");
		mav.addObject("memberDetail", memberDetail);
		mav.addObject("detailMemberPw", detailMemberPw);
		return mav;
	}
}
