package com.liquoreview.controller.admin.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.Pager;
import com.liquoreview.common.SearchCriteria;
import com.liquoreview.model.domain.admin.Auth;
import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.service.member.MemberService;

@RestController
@RequestMapping("/rest")
public class RestAdminMemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	Pager pager;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	// 회원정보관리용 회원목록 조회
	@RequestMapping(value="/admin/member", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JSONObject getMemberList(HttpServletRequest request) {
		logger.info("rest admin member controller 호출 :: member list 조회");
		logger.info("member list 조회 전 페이징 세팅 시작");
				
		List<Member> memberList = null;
		int pageSize = 5;
		JSONObject memberResultObj = new JSONObject();
		
		Criteria criteria = new Criteria(request, pageSize);
		pager.setCriteria(criteria);
		pager.init(memberService.getTotalMemberCnt());
		memberList = memberService.selectMemberList(criteria);
		
		memberResultObj.put("memList", memberList);
		memberResultObj.put("pager", pager);
		logger.info("pager 포함해서 member list 재확인 : "+memberResultObj.toString());
		logger.info("컨트롤러에서 화면에 리턴 전 pager.num재확인 : "+memberResultObj.get("pager"));
		
		return memberResultObj;
	}
	
	@RequestMapping(value="/admin/member/search",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JSONObject getSearchedMemberList(HttpServletRequest request, HttpServletResponse response) {
		String searchType = request.getParameter("searchType");
		String searchWord = request.getParameter("searchWord");
		
		List<Member> searchedMemberList = null;
		int pageSize = 5;
		SearchCriteria searchCriteria = new SearchCriteria(request, pageSize);
		JSONObject memberResultObj = new JSONObject();

		if (searchWord != null && !(searchWord.isEmpty())) {
			searchCriteria.setSearchType(searchType);
			searchCriteria.setSearchWord(searchWord);
			pager.setCriteria(searchCriteria);
			pager.init(memberService.getSearchedMemberCnt(searchCriteria));
			searchedMemberList = memberService.selectSearchedMemberList(searchCriteria);
		} else {
			try {
				response.sendRedirect("/rest/admin/member");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		memberResultObj.put("searchCriteria", searchCriteria);
		memberResultObj.put("memList", searchedMemberList);
		memberResultObj.put("pager", pager);
		return memberResultObj;
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
