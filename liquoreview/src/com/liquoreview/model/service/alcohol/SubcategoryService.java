package com.liquoreview.model.service.alcohol;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.liquoreview.common.Criteria;
import com.liquoreview.model.domain.alcohol.Subcategory;

public interface SubcategoryService {
	public int getSortedSubcateCnt(int topcategory_id);
	public List<Subcategory> selectAll();
	public Subcategory select(int subcategory_id);
	public List<Subcategory> selectAllByTopCate(int topcategory_id);
	public JSONArray selectAllByTopCate(List<Integer> topIdList);
	public List<Subcategory> selectSortedSubcateList(Criteria criteria, int topcategory_id);
	public List<Subcategory> selectByName(String name);
	public List<Subcategory> cateNameCheck(String name);
	public JSONObject insert(Subcategory subcategory);
	public JSONObject update(Subcategory subcategory);
	public void update(List<Subcategory> updateList);
	public void delete(int subcategory_id);
	public void delete(List<Integer> deleteList);
}
