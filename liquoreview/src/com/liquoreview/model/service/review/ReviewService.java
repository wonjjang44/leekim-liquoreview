package com.liquoreview.model.service.review;

import java.util.List;

import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.review.Review;
import com.liquoreview.model.domain.review.ReviewImg;

public interface ReviewService {
	public List<Review> selectAll();
	public Review select(int review_id);
	public List<Review> selectListByMember(int member_id);
	public List<Alcohol> selectListByAlcohol(int alcohol_id);
	public void insert(Review review, ReviewImg reviewImg, String path);
	public void update(Review review, ReviewImg reviewImg, String path);
	public void delete(List<Integer> deleteArray, String path);
	public List<Review> search(String searchWord);
	public List<ReviewImg> getImg();
}
