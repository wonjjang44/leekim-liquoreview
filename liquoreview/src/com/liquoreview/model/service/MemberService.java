package com.liquoreview.model.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.liquoreview.common.PassSecurity;
import com.liquoreview.model.domain.Member;
import com.liquoreview.model.repository.MemberDAO;

@Service("MemberService")
public class MemberService {
	@Resource(name = "MemberDAO")
	private MemberDAO memberDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
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
	 * 회원가입시 로그인 중복체크 여부
	 * @author 이양원
	 * @date 2021. 01. 31  최초생성
	 * @param userid
	 * */
	public String idChk(String userid){
		
		return memberDAO.idChk(userid);
	}
	
	/**
	 * 회원가입
	 * 	회원가입을 할 때 MEMBER 테이블과 MEMBER_PW 테이블 동시에 INSERT 되어야함. Service단에서 새로운 함수를 정의(쿼리 2개 실행)하고 호출하자
	 * @author 이양원
	 * @date 21. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	public int regist(Member allRequestParams) {
		int result = 0;
		
		try {
  			result += memberDAO.registMember(allRequestParams);
			
			if(result > 0) {
				String passSecurity = PassSecurity.encrypt(allRequestParams.getPass());
				allRequestParams.setPass(passSecurity);
				
				result += memberDAO.registMemberPw(allRequestParams);
			}
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return result;
	}
}
