package com.liquoreview.model.service.alcohol;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.SearchCriteria;
import com.liquoreview.exception.DeleteFailException;
import com.liquoreview.exception.EditFailException;
import com.liquoreview.exception.RegistFailException;
import com.liquoreview.model.domain.alcohol.Subcategory;
import com.liquoreview.model.domain.alcohol.Topcategory;
import com.liquoreview.model.repository.alcohol.SubcategoryDAO;
import com.liquoreview.model.repository.alcohol.TopcategoryDAO;

@Service
@Transactional
public class TopcategoryServiceImpl implements TopcategoryService{

	@Autowired
	@Qualifier("mybatisTopcategoryDAO")
	TopcategoryDAO topcategoryDAO;
	
	@Autowired
	@Qualifier("mybatisSubcategoryDAO")
	SubcategoryDAO subcategoryDAO;
	
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
	public JSONObject update(Topcategory topcategory) throws EditFailException{
		List<Subcategory> subList = new ArrayList<Subcategory>();
		int topModiResult = 0;
		int subModiResult = 0;
		JSONObject resultObj = new JSONObject();
		
		//topcategory_id 가진 subList 추출
		subList.addAll(subcategoryDAO.selectAllByTopCate(topcategory.getTopcategory_id()));
		//하위리스트 없으면 topcategory 바로 수정
		if (subList.isEmpty()) {
			topModiResult = topcategoryDAO.update(topcategory);
		} else {
			//하위리스트 있으면 subList 순회 수정 선행
			for (Subcategory sub : subList) {
				logger.info("수정대상으로 추출된 subcategory_id 확인 : "+sub.getSubcategory_id());
				subModiResult = subcategoryDAO.update(sub);
			}
			if (subModiResult != 0) {//sub 수정 성공
				topModiResult = topcategoryDAO.update(topcategory);
				
			} else {
				throw new EditFailException("subcategory 수정 실패");
			}
		}
		if (topModiResult == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "상위카테고리 수정 실패");
			throw new EditFailException("topcategory 수정 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "상위카테고리 수정 성공");
			resultObj.put("topcategory_id", topcategory.getTopcategory_id());
		}

		return resultObj;
	}

	@Override
	public void update(List<Topcategory> updateList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int topcategory_id) throws DeleteFailException{
		// TODO Auto-generated method stub
		int result = topcategoryDAO.delete(topcategory_id);
		if (result ==0) {
			throw new DeleteFailException("topcategory 삭제 실패");
		}
	}

	@Override
	public void delete(List<Integer> deleteList) throws DeleteFailException{
		logger.info(deleteList);
		//List<Subcategory> subList = new ArrayList<Subcategory>();
		int topDelResult = 0;
		int subDelResult = 0;
		
		//deleteList에서 topcategory_id추출
		for (Integer num : deleteList) {
			int topcategory_id = num;
			//topcategory_id를 가진 subList 추출
			//collection의 addAll 메서드는 null파라미터 허용 안하기 때문에
			//subcategory가 없는 topcategory 삭제경우를 대비해 addAll사용 안하는 코드로 변경
			//subList.addAll(subcategoryDAO.selectAllByTopCate(topcategory_id));
			List<Subcategory> subList = subcategoryDAO.selectAllByTopCate(topcategory_id);
			//하위리스트 없으면
			if (subList.isEmpty() || subList == null) {
				//topcategory 바로 삭제
				topDelResult = topcategoryDAO.delete(topcategory_id);
			} else {//하위리스트 있으면
				for (Subcategory sub : subList) {
					//topcategory_id 연결된 subList 삭제
					logger.info("삭제대상으로 추출된 sub.getSubcategory_id 확인 : "+sub.getSubcategory_id());
					subDelResult = subcategoryDAO.delete(sub.getSubcategory_id());
					//subList 삭제 성공 시
				}
				if(subDelResult != 0) {
					//topcategory 삭제
					topDelResult = topcategoryDAO.delete(topcategory_id);
				} else {
					throw new DeleteFailException("subcategory 삭제 실패");
				}
			}
			if (topDelResult == 0) {
				throw new DeleteFailException("topcategory 삭제 실패");
			}
		}
	}

	@Override
	public int getTotalTopcateCnt() {
		return topcategoryDAO.getTotalTopcateCnt();
	}

	@Override
	public int getSearchedTopcateCnt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
