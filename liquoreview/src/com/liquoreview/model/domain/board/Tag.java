package com.liquoreview.model.domain.board;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Tag {
	private int tag_id;
	private String tag_name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy MM dd HH:mm:ss z")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy MM dd HH:mm:ss z")
	private Timestamp last_modi_ymd;
	
	//getters and setters
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
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
		return "Tag [last_modi_ymd=" + last_modi_ymd + ", regdate=" + regdate + ", tag_id=" + tag_id + ", tag_name="
				+ tag_name + "]";
	}
	
	
	
	
}
