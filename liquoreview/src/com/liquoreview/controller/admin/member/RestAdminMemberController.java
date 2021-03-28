package com.liquoreview.controller.admin.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.service.member.MemberService;

@RestController
@RequestMapping("/rest")
public class RestAdminMemberController {
	
	@Autowired
	MemberService memberService;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	// 회원정보관리용 회원목록 조회
	@RequestMapping(value="/admin/member", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public List<Member> getMemberList(HttpServletRequest request) {
		logger.info("rest admin member controller 호출 :: member list 조회");
		List<Member> memList = memberService.selectAll();
		return memList;
	}
	
	//member_id로 회원 1명 상세조회
	@RequestMapping(value="/admin/member/{member_id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public Member getMemberDetail(HttpServletRequest request, @PathVariable("member_id") int member_id) {
		logger.info("전달받은 member_id 확인 : "+member_id);
		Member member = memberService.select(member_id);
		return member;
	}
}
