package com.liquoreview.model.repository.board;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.liquoreview.model.domain.board.Tag;

@Repository
@Transactional
public class MybatisBoardTagDAO implements BoardTagDAO{
	
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public List<Tag> selectAll() {
		return sessionTemplate.selectList("Tag.selectAll");
	}

	@Override
	public Tag select(int tag_id) {
		logger.info("호출받는지 확인");
		return sessionTemplate.selectOne("Tag.select", tag_id);
	}

	@Override
	public int insert(Tag tag) {
		return sessionTemplate.insert("Tag.insert", tag);
	}

	@Override
	public int update(Tag tag) {
		return sessionTemplate.update("Tag.update", tag);
	}

	@Override
	public int delete(int tag_id) {
		return sessionTemplate.delete("Tag.delete", tag_id);
	}
	

}
