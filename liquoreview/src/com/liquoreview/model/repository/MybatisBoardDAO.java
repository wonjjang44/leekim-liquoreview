package com.liquoreview.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liquoreview.model.domain.Board;

@Repository("BoardDAO")
public class MybatisBoardDAO implements BoardDAO{
	@Autowired
	private SqlSessionTemplate session;

	/**
	 * 게시물 전체조회
	 * @author 이양원
	 * @date 2021. 02. 11
	 * @param param
	 * */
	@Override
	public List<Board> lstAllIqr(Board param) {
		
		return session.selectList("lstAllIqr", param);
	}
}
