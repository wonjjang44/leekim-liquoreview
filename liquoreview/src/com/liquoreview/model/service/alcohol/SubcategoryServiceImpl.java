package com.liquoreview.model.service.alcohol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.common.Criteria;
import com.liquoreview.exception.EditFailException;
import com.liquoreview.exception.RegistFailException;
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
	public int getSortedSubcateCnt(int topcategory_id) {
		logger.info("topcategory_id확인 : "+topcategory_id);
		return subcategoryDAO.getSortedSubcateCnt(topcategory_id);
	}
	
	@Override
	public List<Subcategory> selectAll() {
		List<Subcategory> subcateList = subcategoryDAO.selectAll();
		return subcateList;
	}

	@Override
	public Subcategory select(int subcategory_id) {
		return subcategoryDAO.select(subcategory_id);
	}

	@Override
	public List<Subcategory> selectAllByTopCate(int topcategory_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Subcategory> selectSortedSubcateList(Criteria criteria, int topcategory_id) {
		Map<String, Object> subSortingMap = new HashMap<String, Object>();
		subSortingMap.put("criteria", criteria);
		subSortingMap.put("topcategory_id", topcategory_id);
		//List<Subcategory> sortedSubcateList = subcategoryDAO.selectSortedSubcateList(criteria, topcategory_id);
		List<Subcategory> sortedSubcateList = subcategoryDAO.selectSortedSubcateList(subSortingMap);
		logger.info("sortedSubcateList 확인 : "+sortedSubcateList);
		return sortedSubcateList;
	}

	@Override
	public List<Subcategory> selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subcategory> cateNameCheck(String name) {
		return subcategoryDAO.cateNameCheck(name);
	}

	@Override
	public JSONObject insert(Subcategory subcategory) throws RegistFailException{
		int result = subcategoryDAO.insert(subcategory);
		JSONObject resultObj = new JSONObject();
		if (result == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "subcategory 등록 실패");
			throw new RegistFailException("subcategory 등록 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "subcategory 등록 성공");
			resultObj.put("topcategory_id", subcategory.getTopcategory().getTopcategory_id());
		}
		return resultObj;
	}

	@Override
	public JSONObject update(Subcategory subcategory) throws EditFailException{
		int result = subcategoryDAO.update(subcategory);
		JSONObject resultObj = new JSONObject();
		if (result == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "subcategory 수정 실패");
			throw new EditFailException("subcategory 수정 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "subcategory 수정 성공");
		}
		return resultObj;
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
