package com.liquoreview.model.repository.review;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.review.Review;

@Repository
public class MybatisReviewDAO implements ReviewDAO{

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
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
	public int insert(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Review review) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int review_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Review> search(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
