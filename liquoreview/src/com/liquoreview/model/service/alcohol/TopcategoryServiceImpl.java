package com.liquoreview.model.service.alcohol;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.SearchCriteria;
import com.liquoreview.exception.RegistFailException;
import com.liquoreview.model.domain.alcohol.Topcategory;
import com.liquoreview.model.repository.alcohol.TopcategoryDAO;

@Service
@Transactional
public class TopcategoryServiceImpl implements TopcategoryService{

	@Autowired
	@Qualifier("mybatisTopcategoryDAO")
	TopcategoryDAO topcategoryDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Topcategory> selectAll() {
		List<Topcategory> topcateList = topcategoryDAO.selectAll();
		return topcateList;
	}
	
	@Override
	public List<Topcategory> selectTopcateList(Criteria criteria) {
		List<Topcategory> topcateList = topcategoryDAO.selectTopcateList(criteria);
		return topcateList;
	}

	@Override
	public Topcategory select(int topcategory_id) {
		return topcategoryDAO.select(topcategory_id);
	}

	@Override
	public JSONObject insert(Topcategory topcategory) throws RegistFailException{
		int result = topcategoryDAO.insert(topcategory);
		JSONObject resultObj = new JSONObject();
		if (result == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "상위카테고리 등록 실패");
			throw new RegistFailException("상위카테고리 등록 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "상위카테고리 등록 성공");
		}
		return resultObj;
	}

	@Override
	public JSONObject update(Topcategory topcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(List<Topcategory> updateList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int topcategory_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(List<Integer> deleteList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTotalTopcateCnt() {
		return 0;
	}

	@Override
	public int getSearchedTopcateCnt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
