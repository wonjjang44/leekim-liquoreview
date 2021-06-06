package com.liquoreview.model.repository.alcohol;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.alcohol.Subcategory;

@Repository
public class MybatisSubcategoryDAO implements SubcategoryDAO{

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Subcategory> selectAll() {
		return sessionTemplate.selectList("Subcategory.selectSubcateList");
	}

	@Override
	public Subcategory select(int subcategory_id) {
		return sessionTemplate.selectOne("Subcategory", subcategory_id);
	}

}
