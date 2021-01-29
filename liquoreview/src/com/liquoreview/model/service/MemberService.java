package com.liquoreview.model.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liquoreview.model.domain.Member;
import com.liquoreview.model.repository.MemberDAO;

@Service("MemberService")
public class MemberService {
	@Resource(name = "MemberDAO")
	private MemberDAO memberDAO;
	
	
	/**
	 * 로그인
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param allRequestParams
	 * */
	public Member login(Member allRequestParams) {
		
		return memberDAO.login(allRequestParams);
	}
	
	/**
	 * 회원가입
	 * 	회원가입을 할 때 MEMBER 테이블과 MEMBER_PW 테이블 동시에 INSERT 되어야함. Service단에서 새로운 함수를 정의(쿼리 2개 실행)하고 호출하자
	 * @author 이양원
	 * @date 21. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	public int regist(Member allRequestParams) {
		/*트랜잭션 처리 할 것.*/
		int member = memberDAO.registMember(allRequestParams);
		int memberPw = memberDAO.registMemberPw(allRequestParams);
		
		return 0;
	}
}
