package com.liquoreview.model.repository.alcohol;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.alcohol.Topcategory;

@Repository
public class MybatisTopcategoryDAO implements TopcategoryDAO{

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Topcategory> selectAll() {
		return sessionTemplate.selectList("Topcategory.selectTopcateList");
	}

}
