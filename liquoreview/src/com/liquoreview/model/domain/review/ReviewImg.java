package com.liquoreview.model.domain.review;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class ReviewImg {
	private int review_img_id;
	private Review review;
	private String filename;
	private boolean thumb_yn;
	private MultipartFile[] myFile;
	private String[] deleteFile;
	public int getReview_img_id() {
		return review_img_id;
	}
	public void setReview_img_id(int review_img_id) {
		this.review_img_id = review_img_id;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public boolean isThumb_yn() {
		return thumb_yn;
	}
	public void setThumb_yn(boolean thumb_yn) {
		this.thumb_yn = thumb_yn;
	}
	public MultipartFile[] getMyFile() {
		return myFile;
	}
	public void setMyFile(MultipartFile[] myFile) {
		this.myFile = myFile;
	}
	public String[] getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(String[] deleteFile) {
		this.deleteFile = deleteFile;
	}
	
	@Override
	public String toString() {
		return "ReviewImg [review_img_id=" + review_img_id + ", review=" + review + ", filename=" + filename
				+ ", thumb_yn=" + thumb_yn + ", myFile=" + Arrays.toString(myFile) + ", deleteFile="
				+ Arrays.toString(deleteFile) + "]";
	}
	
}
