package com.liquoreview.model.domain.alcohol;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Alcohol {
	private int alcohol_id;
	private Subcategory subcategory;
	private String name;
    private Float degree;
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp regdate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Timestamp last_modi_ymd;

    //getters and setters
	public int getAlcohol_id() {
		return alcohol_id;
	}
	public void setAlcohol_id(int alcohol_id) {
		this.alcohol_id = alcohol_id;
	}
	public Subcategory getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getDegree() {
		return degree;
	}
	public void setDegree(Float degree) {
		this.degree = degree;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
		return "Alcohol [alcohol_id=" + alcohol_id + ", subcategory=" + subcategory + ", name=" + name + ", degree="
				+ degree + ", text=" + text + ", regdate=" + regdate + ", last_modi_ymd=" + last_modi_ymd + "]";
	}
	
	
}
