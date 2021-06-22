package com.liquoreview.model.service.alcohol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.common.Criteria;
import com.liquoreview.exception.DeleteFailException;
import com.liquoreview.exception.EditFailException;
import com.liquoreview.exception.RegistFailException;
import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.alcohol.Subcategory;
import com.liquoreview.model.repository.alcohol.AlcoholDAO;
import com.liquoreview.model.repository.alcohol.SubcategoryDAO;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService{

	@Autowired
	@Qualifier("mybatisSubcategoryDAO")
	SubcategoryDAO subcategoryDAO;
	
	@Autowired
	@Qualifier("mybatisAlcoholDAO")
	AlcoholDAO alcoholDAO;
	
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
		List<Alcohol> alcList = new ArrayList<Alcohol>();
		
		int subModiResult = 0;
		int alcModiResult = 0;
		JSONObject resultObj = new JSONObject();
		
		//subcategory_id 가진 alcList 추출
		logger.info(subcategory.getSubcategory_id());
		logger.info(alcoholDAO.selectAllBySubCate(subcategory.getSubcategory_id()));
		alcList.addAll(alcoholDAO.selectAllBySubCate(subcategory.getSubcategory_id()));
		//하위리스트 없으면 subcategory 바로 수정
		if (alcList.isEmpty()) {
			subModiResult = subcategoryDAO.update(subcategory);
		} else {
			//하위리스트 있으면 alcList 순회 수정 선행
			for(Alcohol alc : alcList) {
				logger.info("수정대상으로 추출된 alcohol_id 확인 : "+alc.getAlcohol_id());
				alcModiResult = alcoholDAO.update(alc);
			}
			if (alcModiResult != 0) {//alc 수정 성공
				subModiResult = subcategoryDAO.update(subcategory);
			} else {
				throw new EditFailException("alcohol 수정 실패");
			}
		}
		if (subModiResult == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "subcategory 수정 실패");
			throw new EditFailException("subcategory 수정 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "subcategory 수정 성공");
			resultObj.put("subcategory_id", subcategory.getSubcategory_id());
		}
		
		return resultObj;
	}

	@Override
	public void update(List<Subcategory> updateList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int subcategory_id) throws DeleteFailException{
		logger.info("param이 topcategory_id인 delete메서드 호출인가?");
		int result = subcategoryDAO.delete(subcategory_id);
		if (result ==0) {
			throw new DeleteFailException("subcategory 삭제 실패");
		}
		
	}

	@Override
	public void delete(List<Integer> deleteList) throws DeleteFailException{
		logger.info(deleteList);
		int subDelResult = 0;
		int alcDelResult = 0;
		//deleteList에서 subcategory_id 추출
		for (Integer num : deleteList) {
			int subcategory_id = num;
			//subcategory_id를 가진 alcoholList추출
			List<Alcohol> alcList = alcoholDAO.selectAllBySubCate(subcategory_id);
			//하위 alcohol list 없으면
			if (alcList.isEmpty() || alcList == null) {
				//subcategory 바로 삭제
				subDelResult = subcategoryDAO.delete(subcategory_id);
			} else {
				//하위 alcohol list 있으면
				/* 
				for (Alcohol alc : alcList) {
					//subcategory_id 연결된 alcohol list 삭제
					logger.info("삭제대상으로 추출된 alc.getAlcohol_id 확인: "+alc.getAlcohol_id() );
					alcDelResult = alcoholDAO.delete(alc.getAlcohol_id());
				}
				*/
				//임시조치
				subDelResult = 0;
				if (alcDelResult != 0) {
					subDelResult = subcategoryDAO.delete(subcategory_id);
				} else {
					throw new DeleteFailException("alcohol 삭제 실패");
				}
			}
			if (subDelResult == 0) {
				throw new DeleteFailException("subcategory 삭제 실패");
			}
		}
		
	}

	@Override
	public JSONArray selectAllByTopCate(List<Integer> topIdList) {
		List<Subcategory> subcateList = null;
		JSONArray subcateArray = new JSONArray();
		for (Integer num : topIdList ) {
			JSONObject resultObj = new JSONObject();
			int topcategory_id = num;
			subcateList = subcategoryDAO.selectAllByTopCate(topcategory_id);
			if (subcateList.isEmpty()) {
				resultObj.put("hasSubBoolean", false);
				resultObj.put("hasSub", "0");
			} else {
				resultObj.put("hasSubBoolean", true);
				resultObj.put("hasSub", "1");
			}
			resultObj.put("testedTopId", topcategory_id);
			subcateArray.add(resultObj);
		}
		
		return subcateArray;
	}

	

}
