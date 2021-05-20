package com.liquoreview.model.service.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.review.Review;
import com.liquoreview.model.domain.review.ReviewImg;
import com.liquoreview.model.repository.review.ReviewDAO;
import com.liquoreview.model.repository.review.ReviewImgDAO;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	@Qualifier("mybatisReviewDAO")
	private ReviewDAO reviewDAO;
	
	@Autowired
	@Qualifier("mybatisReviewImgDAO")
	private ReviewImgDAO reviewImgDAO;
	
	@Override
	public List<Review> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Review select(int review_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> selectListByMember(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alcohol> selectListByAlcohol(int alcohol_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Review review, ReviewImg reviewImg, String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Review review, ReviewImg reviewImg, String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Integer> deleteArray, String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Review> search(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReviewImg> getImg() {
		// TODO Auto-generated method stub
		return null;
	}

}
