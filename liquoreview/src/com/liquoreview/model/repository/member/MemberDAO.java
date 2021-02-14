package com.liquoreview.model.repository.member;

import java.util.List;
import java.util.Map;

import com.liquoreview.model.domain.member.Member;
import com.liquoreview.model.domain.member.MemberPw;

public interface MemberDAO {
	public List<Member> selectAll();
	public List<Member> selectByAuth(int auth_id);
	public Member select(int member_id);
	public Member selectByUserid(String userid);
	public Member loginCheck(Member member);
	public Member idCheck(String userid);
	public Member emailCheck(String email);
	public int memberInsert(Member member);
	public int memberUpdate(Member member);
	public int lastLogUpdate(int member_id);
	public int memberDelete(int member_id);
	public String findId(Member member);
	public String idOverlapCheck(String userid);
	public Member infoCheck(Member member);
	public boolean send(String subject, String text, String from, String to);
	public int updateAuth(Member member);
	public List<Member> search(String searchWord);
}
