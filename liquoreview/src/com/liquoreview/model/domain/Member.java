package com.liquoreview.model.domain;

import java.util.Date;

public class Member extends Member_pw{
	/**
	 * MemberVO
	 * @author 이양원
	 * @date 2021. 01. 29 
	 * */
	
	private String userid;
	private String username;
	private String birth;
	private String email;
	private String phonenum;
	private int hiber_yn; 
	private int del_yn;
	private Date last_login_ymd; 
	private int auth_id;

	public Member() {}

	public Member(Member_pw vo) {
		super.setMember_id(vo.getMember_id());
		super.setRegdate(vo.getRegdate());
		super.setLast_modi_ymd(vo.getLast_modi_ymd());
		
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public int getHiber_yn() {
		return hiber_yn;
	}

	public void setHiber_yn(int hiber_yn) {
		this.hiber_yn = hiber_yn;
	}

	public int getDel_yn() {
		return del_yn;
	}

	public void setDel_yn(int del_yn) {
		this.del_yn = del_yn;
	}

	public Date getLast_login_ymd() {
		return last_login_ymd;
	}

	public void setLast_login_ymd(Date last_login_ymd) {
		this.last_login_ymd = last_login_ymd;
	}

	public int getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(int auth_id) {
		this.auth_id = auth_id;
	}
	
	
	
}
