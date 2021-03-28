package com.liquoreview.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.liquoreview.common.PassSecurity;
import com.liquoreview.model.domain.Member;
import com.liquoreview.model.repository.MemberDAO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService{
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Resource(name = "MemberDAO")
	private MemberDAO memberDAO;
	
	Map<String, String> param = new HashMap<String, String>();
	
	
	/**
	 * 로그인
	 * @author 이양원
	 * @date 2021. 01. 29  최초생성
	 * @param allRequestParams, request
	 * */
	@Override
	public Map<String, String> login(Member allRequestParams, HttpServletRequest request) {
		String msg  = "";
		
		// 입력받은 비밀번호 암호화 후 비교하기
		String encryPassword = PassSecurity.encrypt(allRequestParams.getPASS());
		allRequestParams.setPASS(encryPassword);

		Member member = new Member();
		member = memberDAO.login(allRequestParams);
		
		if(member == null) {
			msg = "idFail";
		}else if(!allRequestParams.getPASS().equals(member.getPASS())) {
			msg = "pwFail";
		}else {
			/*로그인 시 최종로그인일자 업데이트*/
			loginDateUpdate(member.getMEMBER_ID());
			
			HttpSession session = request.getSession();
			session.setAttribute("loginSession", member);
			
			if(member.getAUTH_ID() == 1){
				msg = "admin";
			}else {
				msg = "user";
			}
		}
		
		param.clear();
		param.put("msg", msg);
		
 		return param;
	}
	
	/**
	 * 로그인 시 최종로그인일자 업데이트
	 * @author 이양원
	 * @date 2021. 02. 08
	 * @param param
	 * */
	@Override
	public int loginDateUpdate(int param) {
		
		return memberDAO.loginDateUpdate(param);
	}
	
	/**
	 * 회원가입시 로그인 중복체크 여부
	 * @author 이양원
	 * @date 2021. 01. 31  최초생성
	 * @param userid
	 * */
	@Override
	public String idChk(String userid){
		
		return memberDAO.idChk(userid);
	}
	
	/**
	 * 회원가입
	 * 	회원가입을 할 때 MEMBER 테이블과 MEMBER_PW 테이블 동시에 INSERT 되어야함.
	 * @author 이양원
	 * @date 21. 01. 30  최초생성
	 * @param allRequestParams
	 * */
	@Override
	public int regist(Member allRequestParams) {
		int result = 0;
		
		try {
  			result += memberDAO.registMember(allRequestParams);
			
			if(result > 0) {
				String passSecurity = PassSecurity.encrypt(allRequestParams.getPASS());
				allRequestParams.setPASS(passSecurity);
				
				result += memberDAO.registMemberPw(allRequestParams);
			}
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return result;
	}


}
