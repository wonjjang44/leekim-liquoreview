package com.liquoreview.model.service.member;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.JoinCode;
import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;

public interface MemberService {
	public int getTotalMemberCnt();
	public List<Member> selectMemberList(Criteria criteria);
	public JSONObject adminSelectMemberList(Criteria criteria);
	public List<Member> selectByAuth(int auth_id);
	public Member select(int member_id);
	public Member selectByUserid(String userid);
	public MemberPw selectByMemId(int member_id);
	public Member loginCheck(Member member, MemberPw memberPw);
	public Member idCheck(String userid);
	public Member emailCheck(String email);
	public Member passCheck(String pass);
	public String memberInsert(Member member, MemberPw memberPw);
	public void memberUpdate(Member member);
	public void memberDelete(int member_id);
	public String findId(Member member);
	public String idOverlapCheck(String userid);
	public String resetPass(MemberPw memberPw);
	public JSONObject infoCheck(Member member);
	public boolean send(JoinCode joinCode);
	public JSONObject updateAuth(Member member);
	public List<Member> search(String searchWord);
	
}
