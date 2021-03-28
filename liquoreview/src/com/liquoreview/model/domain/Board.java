package com.liquoreview.model.domain;

import java.util.Date;

public class Board {
	private int BOARD_ID;
	private int TAG_ID;
	private String TAG_NAME;
	private String MEMBER_ID;
	private String USER_NAME;
	private String TITLE;
	private String CONTENT;
	private int ORI_ARTICLE_NO;              //원글번호
	private int ARTICLE_LAYER; 				 //글계층
	private int ARTICLE_ORDER_TO_ORI;  //원글에 대한 순서
	private Date REGDATE;
	private Date LAST_MODI_YMD;
	private int HIT;
	
	public int getBOARD_ID() {
		return BOARD_ID;
	}
	public void setBOARD_ID(int bOARD_ID) {
		BOARD_ID = bOARD_ID;
	}
	public int getTAG_ID() {
		return TAG_ID;
	}
	public void setTAG_ID(int tAG_ID) {
		TAG_ID = tAG_ID;
	}
	public String getTAG_NAME() {
		return TAG_NAME;
	}
	public void setTAG_NAME(String tAG_NAME) {
		TAG_NAME = tAG_NAME;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public int getORI_ARTICLE_NO() {
		return ORI_ARTICLE_NO;
	}
	public void setORI_ARTICLE_NO(int oRI_ARTICLE_NO) {
		ORI_ARTICLE_NO = oRI_ARTICLE_NO;
	}
	public int getARTICLE_LAYER() {
		return ARTICLE_LAYER;
	}
	public void setARTICLE_LAYER(int aRTICLE_LAYER) {
		ARTICLE_LAYER = aRTICLE_LAYER;
	}
	public int getARTICLE_ORDER_TO_ORI() {
		return ARTICLE_ORDER_TO_ORI;
	}
	public void setARTICLE_ORDER_TO_ORI(int aRTICLE_ORDER_TO_ORI) {
		ARTICLE_ORDER_TO_ORI = aRTICLE_ORDER_TO_ORI;
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
	public int getHIT() {
		return HIT;
	}
	public void setHIT(int hIT) {
		HIT = hIT;
	}
	
}
