package com.liquoreview.model.repository.alcohol;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.common.Criteria;
import com.liquoreview.model.domain.alcohol.Alcohol;

@Repository
public class MybatisAlcoholDAO implements AlcoholDAO{

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public int getTotalAlcoholCnt() {
		return sessionTemplate.selectOne("Alcohol.totalAlcoholCnt");
	}
	
	@Override
	public List<Alcohol> selectAll(Criteria criteria) {
		return sessionTemplate.selectList("Alcohol.selectAll", criteria);
	}

	@Override
	public Alcohol select(int alcohol_id) {
		return sessionTemplate.selectOne("Alcohol.select", alcohol_id);
	}

	@Override
	public List<Alcohol> selectAllBySubCate(int subcategory_id) {
		List<Alcohol> alcList = sessionTemplate.selectList("Alcohol.selectBySubCategory", subcategory_id);
		logger.info("dao impl에서 db조회 결과 리턴 직전에 alcohol list 확인 : "+alcList);
		return alcList;
	}

	@Override
	public int insert(Alcohol alcohol) {
		return sessionTemplate.insert("Alcohol.insert", alcohol);
	}

	@Override
	public int update(Alcohol alcohol) {
		return sessionTemplate.update("Alcohol.update", alcohol);
	}

	@Override
	public int delete(int alcohol_id) {
		return sessionTemplate.delete("Alcohol.delete", alcohol_id);
	}

	@Override
	public int insertByExcel(Map<String, Object> paramMap) {
		logger.info("insert query 넘기기 직전 paramMap 확인 : "+paramMap);
		return sessionTemplate.insert("Alcohol.insertByExcel", paramMap);
	}


}
