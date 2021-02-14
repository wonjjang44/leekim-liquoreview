package com.liquoreview.model.repository.member;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;

@Repository
@Transactional
public class MybatisMemberPwDAO implements MemberPwDAO{
	
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	//pk로 pw 1건 조회
	public MemberPw select(int member_pw_id) {
		return null;
	}

	//login pass 체크
	public MemberPw loginCheck(MemberPw memberPw) {
		MemberPw loginPw =  sessionTemplate.selectOne("MemberPw.loginCheck",memberPw);
		logger.info("loginPw확인 : "+loginPw.getPass());
		return loginPw;
	}

	//pw 1건 insert
	public int memberPwInsert(MemberPw memberPw) {
		return sessionTemplate.insert("MemberPw.insertPw", memberPw);
	}

	//pw 재설정
	public int resetPass(MemberPw memberPw) {
		return sessionTemplate.update("MemberPw.resetPass", memberPw);
	}

	//1건 삭제
	public int deletePass(int member_pw_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
