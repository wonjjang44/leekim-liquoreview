package com.liquoreview.model.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.liquoreview.model.domain.Member;

public interface MemberService {
	/**
	 * 로그인
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param allRequestParams
	 * */
	public Map<String, String> login(Member allRequestParams, HttpServletRequest request);
	
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
	 * 회원가입
	 * 	회원가입을 할 때 MEMBER 테이블과 MEMBER_PW 테이블 동시에 INSERT 되어야함.
	 * @author 이양원
	 * @date 21. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	public int regist(Member allRequestParams);
	
	
}
