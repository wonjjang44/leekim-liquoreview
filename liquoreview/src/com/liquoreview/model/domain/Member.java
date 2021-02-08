package com.liquoreview.model.domain;

import java.util.Date;

public class Member extends Member_pw{
	/**
	 * MemberVO
	 * @author 이양원
	 * @date 2021. 01. 29 
	 * */
	
	private String USERID;
	private String USERNAME;
	private String BIRTH;
	private String EMAIL;
	private String PHONENUM;
	private int HIBER_YN; 
	private int DEL_YN;
	private Date LAST_LOGIN_YMD; 
	private int AUTH_ID;
	
	

	public Member() {}

	public Member(Member_pw vo) {
		System.out.println("부모가 가지고있었던 MEMBER_ID : "+vo.getMEMBER_ID());
		
		super.setMEMBER_ID(vo.getMEMBER_ID());
		super.setREGDATE(vo.getREGDATE());
		super.setLAST_MODI_YMD(vo.getLAST_MODI_YMD());
	}


	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getBIRTH() {
		return BIRTH;
	}

	public void setBIRTH(String bIRTH) {
		BIRTH = bIRTH;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getPHONENUM() {
		return PHONENUM;
	}

	public void setPHONENUM(String pHONENUM) {
		PHONENUM = pHONENUM;
	}

	public int getHIBER_YN() {
		return HIBER_YN;
	}

	public void setHIBER_YN(int hIBER_YN) {
		HIBER_YN = hIBER_YN;
	}

	public int getDEL_YN() {
		return DEL_YN;
	}

	public void setDEL_YN(int dEL_YN) {
		DEL_YN = dEL_YN;
	}

	public Date getLAST_LOGIN_YMD() {
		return LAST_LOGIN_YMD;
	}

	public void setLAST_LOGIN_YMD(Date lAST_LOGIN_YMD) {
		LAST_LOGIN_YMD = lAST_LOGIN_YMD;
	}

	public int getAUTH_ID() {
		return AUTH_ID;
	}

	public void setAUTH_ID(int aUTH_ID) {
		AUTH_ID = aUTH_ID;
	}
	
}
