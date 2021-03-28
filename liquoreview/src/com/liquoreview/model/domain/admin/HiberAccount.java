package com.liquoreview.model.domain.admin;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liquoreview.model.domain.member.Member;

public class HiberAccount {
	private int hiber_account_id;
	private Member member;
	private int member_id;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp hiber_start_ymd;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp hiber_end_ymd;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp hiber_restore_ymd;
	
	//getters and setters
	public int getHiber_account_id() {
		return hiber_account_id;
	}
	public void setHiber_account_id(int hiber_account_id) {
		this.hiber_account_id = hiber_account_id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public Timestamp getHiber_start_ymd() {
		return hiber_start_ymd;
	}
	public void setHiber_start_ymd(Timestamp hiber_start_ymd) {
		this.hiber_start_ymd = hiber_start_ymd;
	}
	public Timestamp getHiber_end_ymd() {
		return hiber_end_ymd;
	}
	public void setHiber_end_ymd(Timestamp hiber_end_ymd) {
		this.hiber_end_ymd = hiber_end_ymd;
	}
	public Timestamp getHiber_restore_ymd() {
		return hiber_restore_ymd;
	}
	public void setHiber_restore_ymd(Timestamp hiber_restore_ymd) {
		this.hiber_restore_ymd = hiber_restore_ymd;
	}
}
