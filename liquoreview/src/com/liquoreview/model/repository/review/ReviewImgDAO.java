package com.liquoreview.model.repository.review;

import java.util.List;

import com.liquoreview.model.domain.review.ReviewImg;

public interface ReviewImgDAO {
	public List<ReviewImg> selectAll();
	public ReviewImg select(int review_img_id);
	public int insert(ReviewImg reviewImg);
	public int update(ReviewImg reviewImg);
	public int updateFile(ReviewImg reviewImg);
	public int deleteAllByReview(int review_id);
	public int deleteFile(ReviewImg reviewImg);
	public List<ReviewImg> search(String searchWord);
}
