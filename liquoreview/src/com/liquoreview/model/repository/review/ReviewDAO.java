package com.liquoreview.model.repository.review;

import java.util.List;

import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.review.Review;

public interface ReviewDAO {
	public List<Review> selectAll();
	public Review select(int review_id);
	public List<Review> selectListByMember(int member_id);
	public List<Alcohol> selectListByAlcohol(int alcohol_id);
	public int insert(Review review);
	public int update(Review review);
	public int delete(int review_id);
	public List<Review> search(String searchWord);
}
