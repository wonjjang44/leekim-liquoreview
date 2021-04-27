package com.liquoreview.model.repository;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("excelDAO")
public class MybatisExcelDAO implements ExcelDAO{
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int excelUpload(Map<String, Object> paramMap) {
		return session.insert("excelUpload", paramMap);
	}
	
}
