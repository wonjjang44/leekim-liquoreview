package com.liquoreview.model.domain;

import java.util.Date;

/**
 * Member_pwVO
 * @author 이양원
 * @date 2021. 01. 29
 * */
public class Member_pw {
	
	private int MEMBER_ID;
	private int MEMBER_PW_ID;
	private String PASS;
	private Date REGDATE; 
	private Date LAST_MODI_YMD; 
	private int FIND_PASS_AUTH_CNT;
	private Date FIND_PASS_AUTH_YMD;
	
	public int getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(int mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public int getMEMBER_PW_ID() {
		return MEMBER_PW_ID;
	}
	public void setMEMBER_PW_ID(int mEMBER_PW_ID) {
		MEMBER_PW_ID = mEMBER_PW_ID;
	}
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	public Date getREGDATE() {
		return REGDATE;
	}
	public void setREGDATE(Date rEGDATE) {
		REGDATE = rEGDATE;
	}
	public Date getLAST_MODI_YMD() {
		return LAST_MODI_YMD;
	}
	public void setLAST_MODI_YMD(Date lAST_MODI_YMD) {
		LAST_MODI_YMD = lAST_MODI_YMD;
	}
	public int getFIND_PASS_AUTH_CNT() {
		return FIND_PASS_AUTH_CNT;
	}
	public void setFIND_PASS_AUTH_CNT(int fIND_PASS_AUTH_CNT) {
		FIND_PASS_AUTH_CNT = fIND_PASS_AUTH_CNT;
	}
	public Date getFIND_PASS_AUTH_YMD() {
		return FIND_PASS_AUTH_YMD;
	}
	public void setFIND_PASS_AUTH_YMD(Date fIND_PASS_AUTH_YMD) {
		FIND_PASS_AUTH_YMD = fIND_PASS_AUTH_YMD;
	}
	
}
