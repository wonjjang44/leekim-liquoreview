package com.liquoreview.model.repository.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.model.domain.admin.Auth;

@Repository
@Transactional
public class MybatisAuthDAO implements AuthDAO{
	
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	public List<Auth> selectAll() {
		return sessionTemplate.selectList("Auth.selectAll");
	}

	public Auth select(int auth_id) {
		return sessionTemplate.selectOne("Auth.select", auth_id);
	}

	public Auth selectDefaultAuth() {
		return sessionTemplate.selectOne("Auth.selectDefaultAuth");
	}

	public int insert(Auth auth) {
		return sessionTemplate.insert("Auth.insert", auth);
	}

	public int update(Auth auth) {
		return sessionTemplate.update("Auth.update", auth);
	}

	public int delete(int auth_id) {
		return sessionTemplate.delete("Auth.delete", auth_id);
	}


}
