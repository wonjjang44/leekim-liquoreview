package com.liquoreview.model.repository.alcohol;

import java.util.List;
import java.util.Map;

import com.liquoreview.common.Criteria;
import com.liquoreview.model.domain.alcohol.Subcategory;

public interface SubcategoryDAO {
	public int getTotalSubcategoryCnt();
	public int getSortedSubcateCnt(int topcategory_id);
	public List<Subcategory> selectAll(); 
	public Subcategory select(int subcategory_id);
	public List<Subcategory> selectAllByTopCate(int topcategory_id);
	//public List<Subcategory> selectSortedSubcateList(Criteria criteria, int topcategory_id);
	public List<Subcategory> selectSortedSubcateList(Map<String, Object> subSortingMap);
	public List<Subcategory> selectByName(String name);
	public List<Subcategory> cateNameCheck(String name);
	public int insert(Subcategory subcategory);
	public int update(Subcategory subcategory);
	public int delete(int subcategory_id);
}
