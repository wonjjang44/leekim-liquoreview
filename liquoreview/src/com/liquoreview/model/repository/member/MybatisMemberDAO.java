package com.liquoreview.model.repository.member;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.common.Criteria;
import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;

@Repository
@Transactional
public class MybatisMemberDAO implements MemberDAO {

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	// 페이징 위한 카운트
	@Override
	public int getTotalMemberCnt() {
		return sessionTemplate.selectOne("Member.totalMemberCnt");
	}
	// 전체멤버조회
	public List<Member> selectMemberList(Criteria criteria) {
		logger.info("mybatis memberDAO=======================");
		logger.info("criteria currentPage확인 : "+criteria.getCurrentPage());
		logger.info("criteria pageSize확인 : "+criteria.getPageSize());
		return sessionTemplate.selectList("Member.selectMemberList", criteria);
	}

	// 권한별 멤버조회
	public List<Member> selectByAuth(int auth_id) {
		return sessionTemplate.selectList("Member.selectByAuth", auth_id);
	}

	// 회원의 pk로 특정회원정보조회
	public Member select(int member_id) {
		return sessionTemplate.selectOne("Member.select", member_id);
	}

	// userid로 회원조회
	public Member selectByUserid(String userid) {
		return sessionTemplate.selectOne("Member.selectById", userid);
	}

	// 로긴체크
	public Member loginCheck(Member member) {
		logger.info("mabatisMemberDAO=====요청받은 로그인id : "+member.getUserid());
		Member loginMember = sessionTemplate.selectOne("Member.loginCheck",member);
		logger.info("mabatisMemberDAO=====조회한 userid : "+loginMember.getUserid());
		return loginMember;
	}

	// id중복검사
	public Member idCheck(String userid) {
		return sessionTemplate.selectOne("Member.idCheck", userid);
	}

	//email check
	public Member emailCheck(String email) {
		return sessionTemplate.selectOne("Member.emailCheck", email);
	}

	//register member
	public int memberInsert(Member member) {
		return sessionTemplate.insert("Member.insert", member);
	}

	//update(modify) member's info
	public int memberUpdate(Member member) {
		return sessionTemplate.update("Member.update", member);
	}
	
	@Override
	public int lastLogUpdate(int member_id) {
		return sessionTemplate.update("Member.lastLogUpdate", member_id);
	}

	//delete member's info
	public int memberDelete(int member_id) {
		return sessionTemplate.delete("Member.delete", member_id);
	}

	//id 찾기
	public String findId(Member member) {
		return sessionTemplate.selectOne("Member.findId",member);
	}

	@Override
	public Member infoCheck(Member member) {
		return sessionTemplate.selectOne("Member.infoCheck", member);
	}

	@Override
	public boolean send(String subject, String text, String from, String to) {
		// TODO Auto-generated method stub
		return false;
	}

	// 권한변경
	public int updateAuth(Member member) {
		logger.info("mybatis member dao 에서 확인하는 auth_id : "+member.getAuth().getAuth_id());
		return sessionTemplate.update("Member.updateAuth",member);
	}

	@Override
	public List<Member> search(String searchWord) {
		return sessionTemplate.selectList("Member.search", searchWord);
	}

	@Override
	public String idOverlapCheck(String userid) {
		return sessionTemplate.selectOne("Member.idOverlapCheck",userid);
	}



}
