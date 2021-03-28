package com.liquoreview.model.domain;

import java.util.Date;

/**
 * TopcategoryVO
 * @author 이양원
 * @date 2021. 03. 22  최초생성
 * */
public class Topcategory {
	private int TOPCATEGORY_ID;
	private String TOP_NM;
	private Date REGDATE;
	private Date LAST_MODI_YMD;
	
	public int getTOPCATEGORY_ID() {
		return TOPCATEGORY_ID;
	}
	public void setTOPCATEGORY_ID(int tOPCATEGORY_ID) {
		TOPCATEGORY_ID = tOPCATEGORY_ID;
	}
	public String getTOP_NM() {
		return TOP_NM;
	}
	public void setTOP_NM(String tOP_NM) {
		TOP_NM = tOP_NM;
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
	
}
