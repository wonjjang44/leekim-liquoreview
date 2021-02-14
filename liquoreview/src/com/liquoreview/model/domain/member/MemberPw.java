package com.liquoreview.model.domain.member;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MemberPw {
	private int member_pw_id;
	private Member member;
	private String pass;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp regdate;
	private int reset_pass_auth_cnt;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_modi_ymd;

	//getters and setters
	public int getMember_pw_id() {
		return member_pw_id;
	}
	public void setMember_pw_id(int member_pw_id) {
		this.member_pw_id = member_pw_id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getReset_pass_auth_cnt() {
		return reset_pass_auth_cnt;
	}
	public void setReset_pass_auth_cnt(int reset_pass_auth_cnt) {
		this.reset_pass_auth_cnt = reset_pass_auth_cnt;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	public Timestamp getLast_modi_ymd() {
		return last_modi_ymd;
	}
	public void setLast_modi_ymd(Timestamp last_modi_ymd) {
		this.last_modi_ymd = last_modi_ymd;
	}
}
