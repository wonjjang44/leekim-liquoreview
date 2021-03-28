package com.liquoreview.model.domain;

import java.util.Date;

/**
 * SubcategoryVO
 * @author 이양원
 * @date 2021. 03. 22  최초생성
 * */
public class Subcategory extends Topcategory{
	private int SUBCATEGORY_ID;
	private int TOPCATEGORY_ID;
	private String TOP_NM;
	private String SUB_NM;
	private Date REGDATE;
	private Date LAST_MODI_YMD;
	
	public int getSUBCATEGORY_ID() {
		return SUBCATEGORY_ID;
	}
	public void setSUBCATEGORY_ID(int sUBCATEGORY_ID) {
		SUBCATEGORY_ID = sUBCATEGORY_ID;
	}
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
	public String getSUB_NM() {
		return SUB_NM;
	}
	public void setSUB_NM(String sUB_NM) {
		SUB_NM = sUB_NM;
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
