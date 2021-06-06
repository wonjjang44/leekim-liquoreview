package com.liquoreview.model.service.alcohol;

import java.util.List;

import org.json.simple.JSONObject;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.SearchCriteria;
import com.liquoreview.model.domain.alcohol.Topcategory;

public interface TopcategoryService {
	public int getTotalTopcateCnt();
	public List<Topcategory> selectAll();
	public List<Topcategory> selectTopcateList(Criteria criteria);
	public int getSearchedTopcateCnt(SearchCriteria searchCriteria);
	public Topcategory select(int topcategory_id);
	public JSONObject insert(Topcategory topcategory);
	public JSONObject update(Topcategory topcategory);
	public void update(List<Topcategory> updateList);
	public void delete(int topcategory_id);
	public void delete(List<Integer> deleteList);
}
