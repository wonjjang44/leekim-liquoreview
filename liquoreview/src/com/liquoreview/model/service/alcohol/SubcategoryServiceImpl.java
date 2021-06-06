package com.liquoreview.model.service.alcohol;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.model.domain.alcohol.Subcategory;
import com.liquoreview.model.repository.alcohol.SubcategoryDAO;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService{

	@Autowired
	@Qualifier("mybatisSubcategoryDAO")
	SubcategoryDAO subcategoryDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Subcategory> selectAll() {
		//List<Subcategory> subcateList = subcategoryDAO.
		return null;
	}

	@Override
	public Subcategory select(int subcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subcategory> selectAllByTopCate(int topcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subcategory> selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subcategory> cateNameCheck(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject insert(Subcategory subcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject update(Subcategory subcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(List<Subcategory> updateList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int subcategory_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Integer> deleteList) {
		// TODO Auto-generated method stub
		
	}

}
