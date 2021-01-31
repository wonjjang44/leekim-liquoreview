package com.liquoreview.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.Member;
import com.liquoreview.model.domain.Member_pw;

@Repository("MemberDAO")
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate session;
	
	
	/**
	 * 로그인
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param param
	 * */
	public Member login(Member param) {
		return session.selectOne("login", param);
	}
	
	/**
	 * 회원가입시 로그인 중복체크 여부
	 * @author 이양원
	 * @date 2021. 01. 31  최초생성
	 * @param userid
	 * */
	public String idChk(String userid) {
		return session.selectOne("idChk", userid);
	}
	
	/**
	 * 회원가입(MEMBER)
	 * @author 이양원
	 * @date 21. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	public int registMember(Member allRequestParams) {
		return session.insert("memberReg", allRequestParams);
	}
	
	/**
	 * 회원가입(MEMBER_PW)
	 * @author 이양원
	 * @date 2021. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	public int registMemberPw(Member allRequestParams) {
		return session.insert("memberPwReg", allRequestParams);
	}
}
