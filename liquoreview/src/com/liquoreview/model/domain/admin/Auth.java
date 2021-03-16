package com.liquoreview.model.domain.admin;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Auth {

	private int auth_id;
	private String des;
	private boolean adm_assign;	
	private boolean mem_adm;	
	private boolean cate_adm;	
	private boolean alc_adm;	
	private boolean rev_adm;	
	private boolean rev_comm_adm;	
	private boolean board_adm;	
	private boolean board_comm_adm;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy MM dd HH:mm:ss z")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy MM dd HH:mm:ss z")
	private Timestamp last_modi_ymd;

	//getters and setters
	public int getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(int auth_id) {
		this.auth_id = auth_id;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public boolean isAdm_assign() {
		return adm_assign;
	}
	public void setAdm_assign(boolean adm_assign) {
		this.adm_assign = adm_assign;
	}
	public boolean isMem_adm() {
		return mem_adm;
	}
	public void setMem_adm(boolean mem_adm) {
		this.mem_adm = mem_adm;
	}
	public boolean isCate_adm() {
		return cate_adm;
	}
	public void setCate_adm(boolean cate_adm) {
		this.cate_adm = cate_adm;
	}
	public boolean isAlc_adm() {
		return alc_adm;
	}
	public void setAlc_adm(boolean alc_adm) {
		this.alc_adm = alc_adm;
	}
	public boolean isRev_adm() {
		return rev_adm;
	}
	public void setRev_adm(boolean rev_adm) {
		this.rev_adm = rev_adm;
	}
	public boolean isRev_comm_adm() {
		return rev_comm_adm;
	}
	public void setRev_comm_adm(boolean rev_comm_adm) {
		this.rev_comm_adm = rev_comm_adm;
	}
	public boolean isBoard_adm() {
		return board_adm;
	}
	public void setBoard_adm(boolean board_adm) {
		this.board_adm = board_adm;
	}
	public boolean isBoard_comm_adm() {
		return board_comm_adm;
	}
	public void setBoard_comm_adm(boolean board_comm_adm) {
		this.board_comm_adm = board_comm_adm;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Timestamp getLast_modi_ymd() {
		return last_modi_ymd;
	}
	public void setLast_modi_ymd(Timestamp last_modi_ymd) {
		this.last_modi_ymd = last_modi_ymd;
	}

	
}
