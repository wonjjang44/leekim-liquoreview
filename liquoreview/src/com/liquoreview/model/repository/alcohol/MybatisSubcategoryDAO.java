package com.liquoreview.model.repository.alcohol;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.common.Criteria;
import com.liquoreview.model.domain.alcohol.Subcategory;

@Repository
public class MybatisSubcategoryDAO implements SubcategoryDAO{

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public int getTotalSubcategoryCnt() {
		return 0;
	}

	@Override
	public int getSortedSubcateCnt(int topcategory_id) {
		logger.info("topcategory_id 확인 : "+topcategory_id);
		return sessionTemplate.selectOne("Subcategory.sortedSubcateCnt", topcategory_id);
	}

	//@Override
	//public List<Subcategory> selectSortedSubcateList(Criteria criteria, int topcategory_id) {
		//return sessionTemplate.selectList("Subcategory.sortedSubcateList", criteria, topcategory_id);
		//return sessionTemplate.selectList("Subcategory.sortedSubcateList", topcategory_id, criteria);
		//return null;
	//}

	@Override
	public List<Subcategory> selectSortedSubcateList(Map<String, Object> subSortingMap) {
		return sessionTemplate.selectList("Subcategory.sortedSubcateList", subSortingMap);
	}
	
	
	@Override
	public List<Subcategory> selectAll() {
		//return sessionTemplate.selectList("Subcategory.selectSubcateList");
		return sessionTemplate.selectList("Subcategory.selectAll");
	}

	@Override
	public Subcategory select(int subcategory_id) {
		return sessionTemplate.selectOne("Subcategory.select", subcategory_id);
	}

	@Override
	public List<Subcategory> selectAllByTopCate(int topcategory_id) {
		return sessionTemplate.selectList("Subcategory.selectAllByTopCate", topcategory_id);
	}

	@Override
	public List<Subcategory> selectByName(String name) {
		return sessionTemplate.selectList("Subcategory.selectByName", name);
	}

	@Override
	public List<Subcategory> cateNameCheck(String name) {
		return sessionTemplate.selectList("Subcategory.cateNameCheck", name);
	}

	@Override
	public int insert(Subcategory subcategory) {
		return sessionTemplate.insert("Subcategory.insert", subcategory);
	}

	@Override
	public int update(Subcategory subcategory) {
		logger.info("subcategory 수정요청 넘어온 정보 확인 : "+subcategory);
		int modiResult = sessionTemplate.update("Subcategory.update", subcategory); 
		logger.info("subcategory 수정결과 확인 : "+ modiResult);
		return modiResult;
	}

	@Override
	public int delete(int subcategory_id) {
		logger.info("subcategory 삭제요청 넘어온 subid 확인 : "+subcategory_id);
		int delResult = sessionTemplate.delete("Subcategory.delete", subcategory_id);
		logger.info("subcategory 삭제결과 확인 : "+delResult);
		return delResult;
	}

}
