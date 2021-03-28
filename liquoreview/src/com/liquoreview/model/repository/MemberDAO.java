package com.liquoreview.model.repository;

import com.liquoreview.model.domain.Member;

public interface MemberDAO {
	/**
	 * 로그인
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param param
	 * */
	public Member login(Member param);
	
	/**
	 * 로그인 시 최종로그인일자 업데이트
	 * @author 이양원
	 * @date 2021. 02. 08
	 * @param param
	 * */
	public int loginDateUpdate(int param);

	/**
	 * 회원가입시 로그인 중복체크 여부
	 * @author 이양원
	 * @date 2021. 01. 31  최초생성
	 * @param userid
	 * */
	public String idChk(String userid);
	
	/**
	 * 회원가입(MEMBER)
	 * @author 이양원
	 * @date 21. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	public int registMember(Member allRequestParams);
	
	/**
	 * 회원가입(MEMBER_PW)
	 * @author 이양원
	 * @date 2021. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	public int registMemberPw(Member allRequestParams);
}
