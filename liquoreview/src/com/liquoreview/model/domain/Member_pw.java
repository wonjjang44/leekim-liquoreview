package com.liquoreview.model.domain;

import java.util.Date;

public class Member_pw {
	/**
	 * Member_pwVO
	 * @author 이양원
	 * @date 2021. 01. 29
	 * */
	
	private int member_pw_id;
	private int  member_id;
	private String pass;
	private Date regdate;
	private Date last_modi_ymd;
	private int find_pass_auth_cnt;
	private Date find_pass_auth_ymd;
	
	public int getMember_pw_id() {
		return member_pw_id;
	}
	public void setMember_pw_id(int member_pw_id) {
		this.member_pw_id = member_pw_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getLast_modi_ymd() {
		return last_modi_ymd;
	}
	public void setLast_modi_ymd(Date last_modi_ymd) {
		this.last_modi_ymd = last_modi_ymd;
	}
	public int getFind_pass_auth_cnt() {
		return find_pass_auth_cnt;
	}
	public void setFind_pass_auth_cnt(int find_pass_auth_cnt) {
		this.find_pass_auth_cnt = find_pass_auth_cnt;
	}
	public Date getFind_pass_auth_ymd() {
		return find_pass_auth_ymd;
	}
	public void setFind_pass_auth_ymd(Date find_pass_auth_ymd) {
		this.find_pass_auth_ymd = find_pass_auth_ymd;
	}
	
	
}
