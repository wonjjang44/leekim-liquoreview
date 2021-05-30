package com.liquoreview.model.domain.alcohol;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

public class AlcImg {
	private int alc_img_id;
	private Alcohol alcohol;
	private String filename;
	private boolean tuumb_yn;
	private MultipartFile[] alcImgFile;
	private String[] deleteFile;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp regdate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp last_modi_ymd;

	//getters and setters
	public int getAlc_img_id() {
		return alc_img_id;
	}
	public void setAlc_img_id(int alc_img_id) {
		this.alc_img_id = alc_img_id;
	}
	public Alcohol getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(Alcohol alcohol) {
		this.alcohol = alcohol;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isTuumb_yn() {
		return tuumb_yn;
	}
	public void setTuumb_yn(boolean tuumb_yn) {
		this.tuumb_yn = tuumb_yn;
	}
	public MultipartFile[] getAlcImgFile() {
		return alcImgFile;
	}
	public void setAlcImgFile(MultipartFile[] alcImgFile) {
		this.alcImgFile = alcImgFile;
	}
	public String[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
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
		return "AlcImg [alc_img_id=" + alc_img_id + ", alcohol=" + alcohol + ", filename=" + filename + ", tuumb_yn="
				+ tuumb_yn + ", alcImgFile=" + Arrays.toString(alcImgFile) + ", deleteFile="
				+ Arrays.toString(deleteFile) + ", regdate=" + regdate + ", last_modi_ymd=" + last_modi_ymd + "]";
	}
}
