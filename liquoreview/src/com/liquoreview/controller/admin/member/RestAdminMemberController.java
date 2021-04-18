package com.liquoreview.controller.admin.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.model.domain.admin.Auth;
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
	//	public String getMemberList(HttpServletRequest request) {
	public List<Member> getMemberList(HttpServletRequest request) {
		logger.info("rest admin member controller 호출 :: member list 조회");
		List<Member> memList = memberService.selectAll();
		logger.info("member list 확인 : "+memList);
		//JSONObject memList = memberService.adminSelectAll();
		//return memList.toString();
		return memList;
	}
	
	//회원정보관리 상세페이지에서 회원 권한 수정
	@RequestMapping(value="/admin/member/{member_id}/{auth_id}", method = RequestMethod.PATCH, produces = "application/text;charset=UTF-8")
	public String updateMemberAuth(@PathVariable(value="member_id") int member_id, @PathVariable(value="auth_id") int auth_id) {
		Member member = new Member();
		member.setAuth(new Auth());
		member.setMember_id(member_id);
		member.getAuth().setAuth_id(auth_id);
		JSONObject editMemAuthResult = memberService.updateAuth(member);
		return editMemAuthResult.toString();
	}
	
}
