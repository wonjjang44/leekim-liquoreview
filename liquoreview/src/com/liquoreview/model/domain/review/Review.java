package com.liquoreview.model.domain.review;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.member.Member;

public class Review {
	private int review_id;
	private Alcohol alcohol;
	private Member member;
	private String title;
	private String detail;
	private int score;
	private int hit;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_modi_ymd;
	
	//getters and setters
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public Alcohol getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(Alcohol alcohol) {
		this.alcohol = alcohol;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
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
		return "Review [review_id=" + review_id + ", alcohol=" + alcohol + ", member=" + member + ", title=" + title
				+ ", detail=" + detail + ", score=" + score + ", hit=" + hit + ", regdate=" + regdate
				+ ", last_modi_ymd=" + last_modi_ymd + "]";
	}
	
	
}
