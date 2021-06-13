package com.liquoreview.model.domain.alcohol;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Subcategory {
	private int subcategory_id;
	private Topcategory topcategory;
	private String subname;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_modi_ymd;

	//getters and setters
	public int getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(int subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public Topcategory getTopcategory() {
		return topcategory;
	}
	public void setTopcategory(Topcategory topcategory) {
		this.topcategory = topcategory;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
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
		return "Subcategory [subcategory_id=" + subcategory_id + ", topcategory=" + topcategory + ", subname=" + subname
				+ ", regdate=" + regdate + ", last_modi_ymd=" + last_modi_ymd + "]";
	}
}
