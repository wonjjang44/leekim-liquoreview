package com.liquoreview.model.repository.review;

import java.util.List;

import com.liquoreview.model.domain.review.RComment;

public interface RCommentDAO {
	public List<RComment> selectAll();
	public RComment select(int r_comment_id);
	public List<RComment> selectListByReview(int review_id);
	public List<RComment> selectListByMember(int member_id);
	public int insert(RComment rComment);
	public int update(RComment rComment);
	public int delete(int r_comment_id);
	public List<RComment> search(String searchWord);
}
