package com.liquoreview.model.domain.admin;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liquoreview.model.domain.member.Member;

public class DelAccount {
	private int del_account_id;
	private Member member;
	private Auth auth;
	private int member_id;
	private String userid;
	private String username;
	private String birth;
	private String email;
	private String phonenum;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp regdate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_modi_ymd;
	private  boolean hiber_yn;
	private boolean del_yn;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_login_ymd;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_del_modi_ymd;
}
