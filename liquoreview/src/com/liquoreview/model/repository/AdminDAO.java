package com.liquoreview.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("AdminDAO")
public class AdminDAO {
	@Autowired
	private SqlSessionTemplate session;
}
