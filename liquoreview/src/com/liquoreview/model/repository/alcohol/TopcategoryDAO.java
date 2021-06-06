package com.liquoreview.model.repository.alcohol;

import java.util.List;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.SearchCriteria;
import com.liquoreview.model.domain.alcohol.Topcategory;

public interface TopcategoryDAO {
	public int getTotalTopcateCnt();
	public List<Topcategory> selectAll();
	public List<Topcategory> selectTopcateList(Criteria criteria);
	public int getSearchedTopcateCnt(SearchCriteria searchCriteria);
	public Topcategory select(int topcategory_id);
	public int insert(Topcategory topcategory);
	public int update(Topcategory topcategory);
	public int delete(int topcategory_id);
}
