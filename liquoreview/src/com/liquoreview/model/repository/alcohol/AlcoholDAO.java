package com.liquoreview.model.repository.alcohol;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.liquoreview.model.domain.alcohol.Alcohol;

public interface AlcoholDAO {
	public List<Alcohol> selectAll();
	public Alcohol select(int alcohol_id);
	public List<Alcohol> selectAllBySubCate(int subcategory_id);
	public int insert(Alcohol alcohol);
	public int update(Alcohol alcohol);
	public int delete(int alcohol_id);
}
