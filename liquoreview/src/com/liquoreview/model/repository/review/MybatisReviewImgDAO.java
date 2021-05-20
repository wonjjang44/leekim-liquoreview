package com.liquoreview.model.repository.review;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.review.ReviewImg;

@Repository
public class MybatisReviewImgDAO implements ReviewImgDAO{
	
	@Autowired
	SqlSessionTemplate sessionTemplate;

	@Override
	public List<ReviewImg> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewImg select(int review_img_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ReviewImg reviewImg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(ReviewImg reviewImg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateFile(ReviewImg reviewImg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAllByReview(int review_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteFile(ReviewImg reviewImg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ReviewImg> search(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
