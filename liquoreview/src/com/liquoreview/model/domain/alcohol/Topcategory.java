package com.liquoreview.model.domain.alcohol;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Topcategory {
	private int topcategory_id;
	private String topname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_modi_ymd;
	
	
	//getters and setters
	public int getTopcategory_id() {
		return topcategory_id;
	}
	public void setTopcategory_id(int topcategory_id) {
		this.topcategory_id = topcategory_id;
	}
	public String getTopname() {
		return topname;
	}
	public void setTopname(String topname) {
		this.topname = topname;
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
	
	@Override
	public String toString() {
		return "Topcategory [topcategory_id=" + topcategory_id + ", topname=" + topname + ", regdate=" + regdate
				+ ", last_modi_ymd=" + last_modi_ymd + "]";
	}
}
