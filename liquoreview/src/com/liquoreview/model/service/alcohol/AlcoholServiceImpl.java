package com.liquoreview.model.service.alcohol;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.repository.alcohol.AlcoholDAO;

@Service
@Transactional
public class AlcoholServiceImpl implements AlcoholService{
	
	@Autowired
	@Qualifier("mybatisAlcoholDAO")
	AlcoholDAO alcoholDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Alcohol> selectAll() {
		List<Alcohol> alcoholList = alcoholDAO.selectAll();
		return alcoholList;
	}

	@Override
	public Alcohol select(int alcohol_id) {
		return alcoholDAO.select(alcohol_id);
	}

	@Override
	public List<Alcohol> selectAllBySubCate(int subcategory_id) {
		return alcoholDAO.selectAllBySubCate(subcategory_id);
	}

	@Override
	public JSONArray selectAllBySubCate(List<Integer> subIdList) {
		List<Alcohol> alcoholList = null;
		JSONArray alcoholArray = new JSONArray();
		for (Integer num : subIdList) {
			JSONObject resultObj = new JSONObject();
			int subcategory_id = num;
			alcoholList = alcoholDAO.selectAllBySubCate(subcategory_id);
			if (alcoholList.isEmpty()) {
				resultObj.put("hasAlcBoolean", false);
				resultObj.put("hasAlc", "0");
			} else {
				resultObj.put("hasAlcBoolean", true);
				resultObj.put("hasAlc", "1");
			}
			resultObj.put("testedSubId", subcategory_id);
			alcoholArray.add(resultObj);
		}
		
		return alcoholArray;
	}

	@Override
	public JSONObject insert(Alcohol alcohol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update(Alcohol alcohol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int alcohol_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Integer> deleteList) {
		// TODO Auto-generated method stub
		
	}

}
