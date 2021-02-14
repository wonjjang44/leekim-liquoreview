package com.liquoreview.common;

import org.springframework.stereotype.Component;

@Component
public class JoinCode {
	private String userid;
	private String email;
	private String num;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
}
