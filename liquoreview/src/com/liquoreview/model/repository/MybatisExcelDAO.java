package com.liquoreview.model.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class MybatisExcelDAO implements ExcelDAO{
	
	@Autowired
	SqlSessionTemplate sessionTemplate;

	@Override
	public List<Object> getMembers(Map<String, Object> searchMap) {
		return sessionTemplate.selectList("Member.selectExcel",searchMap);
	}
}
