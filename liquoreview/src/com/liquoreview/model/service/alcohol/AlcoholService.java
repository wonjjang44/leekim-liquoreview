package com.liquoreview.model.service.alcohol;

import java.io.File;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.liquoreview.common.Criteria;
import com.liquoreview.model.domain.alcohol.Alcohol;

public interface AlcoholService {
	public int getTotalAlcoholCnt();
	public List<Alcohol> selectAll(Criteria criteria);
	public Alcohol select(int alcohol_id);
	public List<Alcohol> selectAllBySubCate(int subcategory_id);
	public JSONArray selectAllBySubCate(List<Integer> subIdList);
	public JSONObject insert(Alcohol alcohol);
	public JSONObject insertByExcel(File destFile);
	public JSONObject update(Alcohol alcohol);
	public void delete(int alcohol_id);
	public void delete(List<Integer> deleteList);
}
