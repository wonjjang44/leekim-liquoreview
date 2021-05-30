package com.liquoreview.model.repository.review;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.review.RComment;

@Repository
public class MybatisRCommentDAO implements RCommentDAO{

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<RComment> selectAll() {
		return null;
	}

	@Override
	public RComment select(int r_comment_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RComment> selectListByReview(int review_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RComment> selectListByMember(int member_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(RComment rComment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(RComment rComment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int r_comment_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RComment> search(String searchWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
