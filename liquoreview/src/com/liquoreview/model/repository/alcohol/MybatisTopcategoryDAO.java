package com.liquoreview.model.repository.alcohol;

import java.util.List;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.common.Criteria;
import com.liquoreview.common.SearchCriteria;
import com.liquoreview.model.domain.alcohol.Topcategory;

@Repository
public class MybatisTopcategoryDAO implements TopcategoryDAO{

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public int getTotalTopcateCnt() {
		return sessionTemplate.selectOne("Topcategory.totalTopcateCnt");
	}
	
	@Override
	public List<Topcategory> selectAll() {
		return sessionTemplate.selectList("Topcategory.selectAll");
	}
	
	@Override
	public List<Topcategory> selectTopcateList(Criteria criteria) {
		return sessionTemplate.selectList("Topcategory.selectTopcateList", criteria);
	}

	@Override
	public Topcategory select(int topcategory_id) {
		return sessionTemplate.selectOne("Topcategory.select", topcategory_id);
	}

	@Override
	public int insert(Topcategory topcategory) {
		return sessionTemplate.insert("Topcategory.insert", topcategory);
	}

	@Override
	public int update(Topcategory topcategory) {
		return sessionTemplate.update("Topcategory.update", topcategory);
	}

	@Override
	public int delete(int topcategory_id) {
		return sessionTemplate.delete("Topcategory.delete", topcategory_id);
	}

	@Override
	public int getSearchedTopcateCnt(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return 0;
	}

}
