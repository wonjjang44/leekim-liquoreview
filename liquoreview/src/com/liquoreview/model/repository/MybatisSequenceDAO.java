package com.liquoreview.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("SequenceDAO")
public class MybatisSequenceDAO implements SequenceDAO{
	@Autowired
	private SqlSessionTemplate session;

	
	/**
	 * 각 테이블의 pk 시퀀스를 가져온다
	 * @author 이양원
	 * @date 2021. 06. 12
	 * @param
	 * */
	@Override
	public int selectAlcImgSequence() {
		
		return session.selectOne("selectAlcImgSequence");
	}
}
