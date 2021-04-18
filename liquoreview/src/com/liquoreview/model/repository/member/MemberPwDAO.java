package com.liquoreview.model.repository.member;

import com.liquoreview.model.domain.member.MemberPw;

public interface MemberPwDAO {
	public MemberPw select(int member_pw_id);
	public MemberPw selectByMemberId(int member_id);
	public MemberPw loginCheck(MemberPw memberPw);
	public int memberPwInsert(MemberPw memberPw);
	public int resetPass(MemberPw memberPw);
	public int deletePass(int member_pw_id);
}
