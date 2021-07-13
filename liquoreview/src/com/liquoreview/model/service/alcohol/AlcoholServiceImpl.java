package com.liquoreview.model.service.alcohol;

import java.io.File;
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
import com.liquoreview.common.excel.AlcExcelValueConverter;
import com.liquoreview.common.excel.ExcelReadOption;
import com.liquoreview.common.excel.ExcelReader;
import com.liquoreview.model.domain.alcohol.Alcohol;
import com.liquoreview.model.domain.alcohol.Subcategory;
import com.liquoreview.model.domain.alcohol.Topcategory;
import com.liquoreview.model.repository.alcohol.AlcoholDAO;
import com.liquoreview.model.repository.alcohol.SubcategoryDAO;
import com.liquoreview.model.repository.alcohol.TopcategoryDAO;

@Service
@Transactional
public class AlcoholServiceImpl implements AlcoholService{
	@Autowired
	@Qualifier("mybatisTopcategoryDAO")
	TopcategoryDAO topcategoryDAO;
	@Autowired
	@Qualifier("mybatisSubcategoryDAO")
	SubcategoryDAO subcategoryDAO;
	
	@Autowired
	@Qualifier("mybatisAlcoholDAO")
	AlcoholDAO alcoholDAO;
	
	@Autowired
	ExcelReadOption excelReadOption;
	
	@Autowired
	AlcExcelValueConverter alcExcelValueConverter;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public int getTotalAlcoholCnt() {
		return alcoholDAO.getTotalAlcoholCnt();
	}
	
	@Override
	public List<Alcohol> selectAll(Criteria criteria) {
		List<Alcohol> alcoholList = alcoholDAO.selectAll(criteria);
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

	@Override
	public JSONObject insertByExcel(File destFile) {
		int insertResult = 0;
		JSONObject resultObj = new JSONObject();
		int startRow = 2;
		//get category list
		List<Topcategory> topcateList = topcategoryDAO.selectAll();
		List<Subcategory> subcateList = subcategoryDAO.selectAll();

		logger.info("업로드 요청받은 destFile확인 : "+destFile);
		
		//file path 추가
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		//추출할 컬럼명 추가
		excelReadOption.setOutputColumns("A","B","C","D","E");
		//sheet에서 데이터 추출 시작행 세팅
		excelReadOption.setStartRow(startRow);
		
		List<Map<String, Object>> excelContent = ExcelReader.read(excelReadOption);
		
		logger.info("ExcelReader로 읽어들인 excelContent 확인 : "+excelContent);
		
		//excelContent 변환
		List<Map<String, Object>> convertedExcelContent = alcExcelValueConverter.convertValue(topcateList, subcateList, excelContent);
		logger.info("convertedExcelContent 확인 : "+convertedExcelContent);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("excelContent", convertedExcelContent);
		insertResult = alcoholDAO.insertByExcel(paramMap);
		logger.info("excel insert 결과확인 : "+insertResult);
		if (insertResult == 0) {
			resultObj.put("resultCode", "0");
			resultObj.put("msg", "등록 실패");
		} else {
			resultObj.put("resultCode", "1");
			resultObj.put("msg", "alcohol list 등록 성공");
		}
		return resultObj;
	}

}
